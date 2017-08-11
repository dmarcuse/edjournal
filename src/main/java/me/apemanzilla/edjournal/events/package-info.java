/**
 * This package contains classes for every event defined in the player journal
 * manual. Only some of these classes are documented, since almost all of them
 * follow the specifications set in the manual.<br>
 * <br>
 * <a href=
 * "http://hosting.zaonce.net/community/journal/v11/Journal_Manual_v11.pdf">Official
 * Journal Manual V11</a> <br>
 * <br>
 * Please note that there are several conventions followed by events defined in
 * this package.<br>
 * <br>
 * First, the field and class names attempt to follow the names used in the
 * manual and the log data.<br>
 * <br>
 * Second, all fields of events are <code>private</code>. You should use the
 * generated getter methods to retrieve values instead, and should not set
 * values.<br>
 * <br>
 * Third, members annotated with {@link javax.annotation.Nullable Nullable} may
 * produce <code>null</code> values in standard usage. Usually, this is because
 * of something described in the official manual linked above. You should ensure
 * that your code handles these situations appropriately.<br>
 * <br>
 * Finally, many events share similar data structures or constants. Classes and
 * enums for many of these cases can be found in
 * {@link me.apemanzilla.edjournal.gameobjects}.<br>
 * <br>
 * <h2>Special cases</h2> Certain events have special behavior associated with
 * them:<br>
 * {@link me.apemanzilla.edjournal.events.Scan Scan}<br>
 * {@link me.apemanzilla.edjournal.events.UnknownJournalEvent
 * UnknownJournalEvent}
 * 
 * @author apemanzilla
 */
package me.apemanzilla.edjournal.events;
