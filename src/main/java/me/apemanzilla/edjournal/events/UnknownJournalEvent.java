package me.apemanzilla.edjournal.events;

import lombok.*;

/**
 * Created when a complete {@link JournalEvent} implementation is not available
 * for a given event. This will also be accompanied with a warning logged via
 * slf4j.<br>
 * <br>
 * If you encounter this in normal usage please report it! It most likely means
 * I missed an event from the manual or didn't implement something properly.
 * 
 * @author apemanzilla
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class UnknownJournalEvent extends JournalEvent {}
