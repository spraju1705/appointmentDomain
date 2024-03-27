package com.neudesic.appointmentmanagementsystem.domain.entities;


import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class AggregateRoot extends Entity{
    public AggregateRoot(UUID id) {
        super(id);
    }

    //    private final List<PatientDomainEvent> changes = new ArrayList<>();
//    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());
//
//    public List<PatientDomainEvent> getUncommittedChanges() {
//        return this.changes;
//    }
//
//    public void markChangesAsCommitted() {
//        this.changes.clear();
//    }

    public abstract <R extends AppointmentDomainEvent> void apply(R event);


//    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
//        try {
//            var method = getClass().getDeclaredMethod("apply", event.getClass());
//            method.setAccessible(true);
//            method.invoke(this, event);
//        } catch (NoSuchMethodException e) {
//            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
//        } finally {
//            if (isNewEvent) {
//                changes.add(event);
//            }
//        }
//    }

//    public void raiseEvent(BaseEvent event) {
//        applyChange(event, true);
//    }
//
//    public void replayEvents(Iterable<BaseEvent> events) {
//        events.forEach(event -> applyChange(event, false));
//    }

}
