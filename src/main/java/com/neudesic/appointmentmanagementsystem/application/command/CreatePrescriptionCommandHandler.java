package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.Status;
import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Prescription;
import com.neudesic.appointmentmanagementsystem.domain.event.PrescriptionCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PatientID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionDetails;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionStatus;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.PrescriptionEntity;
import com.neudesic.appointmentmanagementsystem.infrastructure.http.PatientDomainExternalServiceRetriever;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.AppointmentRepository;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.DoctorRepository;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePrescriptionCommandHandler implements CommandHandlerMethod<CreatePrescriptionCommand>{
    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    PatientDomainExternalServiceRetriever patientDomainExternalServiceRetriever;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public UpdateResponse handle(CreatePrescriptionCommand command) {
        var appointmentDBEntity = appointmentRepository.findById(command.getDto().getAppointmentID());
        if (appointmentDBEntity.isEmpty()){
            throw new AppointmentDomainException("Invalid Appointment ID");
        }
        var doctor = doctorRepository.findById(appointmentDBEntity.get().getDoctor().getId());
        if (doctor.isEmpty()){
            throw new AppointmentDomainException("Invalid Doctor ID");
        }
        patientDomainExternalServiceRetriever.getPatientInfo(appointmentDBEntity.get().getPatientID());
        var prescriptionDetails =
                PrescriptionDetails.builder().
                        medicine(command.getDto().getPrescriptionDetails().getMedicine()).dozage(command.getDto().getPrescriptionDetails().getDozage()).build();
        Prescription prescription= Prescription.builder().id(command.getId()).prescriptionStatus(PrescriptionStatus.CREATED).prescriptionDetails(prescriptionDetails).patientID(new PatientID(appointmentDBEntity.get().getPatientID())).doctorID(new DoctorID(appointmentDBEntity.get().getDoctor().getId())).build();
        prescription.apply(new PrescriptionCreateEvent());
        var prescriptionToDB = modelMapper.map(prescription, PrescriptionEntity.class);
        prescriptionToDB.setDoctor(doctor.get());
        prescriptionToDB.setAppointment(appointmentDBEntity.get());
        var  prescriptionFromDB = prescriptionRepository.save(prescriptionToDB);
        appointmentDBEntity.get().setPrescriptionEntity(prescriptionFromDB);
        appointmentRepository.flush();
        return UpdateResponse.builder().status(Status.SUCCESS).id(prescriptionFromDB.getId()).message("Prescription created successfully").build();
    }
}
