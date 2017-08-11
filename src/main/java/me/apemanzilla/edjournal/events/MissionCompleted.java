package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.CargoItem;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MissionCompleted extends JournalEvent {
	private String name;
	private String faction;
	private long missionID;
	
	@Nullable
	private String commodity;
	
	@Nullable
	private int count;
	
	@Nullable
	private String target;
	
	@Nullable
	private String targetType;
	
	@Nullable
	private String targetFaction;
	
	@Nullable
	private Long reward;
	
	@Nullable
	private Long donation;
	
	private List<String> permitsAwarded = Collections.emptyList();
	private List<CargoItem> commodityReward = Collections.emptyList();
}
