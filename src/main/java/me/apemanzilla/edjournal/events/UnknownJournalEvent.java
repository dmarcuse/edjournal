package me.apemanzilla.edjournal.events;

import lombok.*;

/**
 * Created when a complete {@link JournalEvent} implementation is not available
 * for a given event. This will also be accompanied with a warning logged via
 * slf4j.
 * 
 * @author apemanzilla
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class UnknownJournalEvent extends JournalEvent {}
