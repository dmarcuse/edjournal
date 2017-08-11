package me.apemanzilla.edjournal;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

import lombok.extern.slf4j.Slf4j;
import me.apemanzilla.edjournal.events.*;

@Slf4j
class JournalEventDeserializer implements JsonDeserializer<JournalEvent> {
	private static final Map<String, Class<? extends JournalEvent>> eventClasses;

	private static void add(Class<? extends JournalEvent> eventClass) {
		eventClasses.put(eventClass.getSimpleName(), eventClass);
	}
	
	static {
		eventClasses = new HashMap<>();
		
		add(ApproachSettlement.class);
		add(Bounty.class);
		add(BuyAmmo.class);
		add(BuyDrones.class);
		add(BuyExplorationData.class);
		add(BuyTradeData.class);
		add(CapShipBond.class);
		add(Cargo.class);
		add(ChangeCrewRole.class);
		add(ClearSavedGame.class);
		add(CockpitBreached.class);
		add(CollectCargo.class);
		add(CommitCrime.class);
		add(CommunityGoalDiscard.class);
		add(CommunityGoalJoin.class);
		add(CommunityGoalReward.class);
		add(Continued.class);
		add(CrewAssign.class);
		add(CrewFire.class);
		add(CrewHire.class);
		add(CrewLaunchFighter.class);
		add(CrewMemberJoins.class);
		add(CrewMemberQuits.class);
		add(CrewMemberRoleChange.class);
		add(DatalinkScan.class);
		add(DatalinkVoucher.class);
		add(DataScanned.class);
		add(Died.class);
		add(Docked.class);
		add(DockFighter.class);
		add(DockingCancelled.class);
		add(DockingDenied.class);
		add(DockingGranted.class);
		add(DockingRequested.class);
		add(DockingTimeout.class);
		add(DockSRV.class);
		add(EjectCargo.class);
		add(EndCrewSession.class);
		add(EngineerApply.class);
		add(EngineerContribution.class);
		add(EngineerCraft.class);
		add(EngineerProgress.class);
		add(EscapeInterdiction.class);
		add(FactionKillBond.class);
		add(FetchRemoteModule.class);
		add(Fileheader.class);
		add(Friends.class);
		add(FuelScoop.class);
		add(FSDJump.class);
		add(HeatDamage.class);
		add(HeatWarning.class);
		add(HullDamage.class);
		add(Interdicted.class);
		add(Interdiction.class);
		add(JetConeBoost.class);
		add(JetConeDamage.class);
		add(JoinACrew.class);
		add(KickCrewMember.class);
		add(LaunchFighter.class);
		add(LaunchSRV.class);
		add(Liftoff.class);
		add(LoadGame.class);
		add(Loadout.class);
		add(Location.class);
		add(MarketBuy.class);
		add(MarketSell.class);
		add(MaterialCollected.class);
		add(MaterialDiscarded.class);
		add(MaterialDiscovered.class);
		add(Materials.class);
		add(MiningRefined.class);
		add(MissionAbandoned.class);
		add(MissionAccepted.class);
		add(MissionCompleted.class);
		add(MissionFailed.class);
		add(ModuleBuy.class);
		add(ModuleRetrieve.class);
		add(ModuleSell.class);
		add(ModuleSellRemote.class);
		add(ModuleStore.class);
		add(ModuleSwap.class);
		add(NewCommander.class);
		add(Passengers.class);
		add(PayFines.class);
		add(PayLegacyFines.class);
		add(PowerplayCollect.class);
		add(PowerplayDefect.class);
		add(PowerplayDeliver.class);
		add(PowerplayFastTrack.class);
		add(PowerplayJoin.class);
		add(PowerplayLeave.class);
		add(PowerplaySalary.class);
		add(PowerplayVote.class);
		add(PowerplayVoucher.class);
		add(Progress.class);
		add(Promotion.class);
		add(PVPKill.class);
		add(QuitACrew.class);
		add(Rank.class);
		add(RebootRepair.class);
		add(ReceiveText.class);
		add(RedeemVoucher.class);
		add(RefuelAll.class);
		add(RefuelPartial.class);
		add(Repair.class);
		add(RepairAll.class);
		add(Resurrect.class);
		add(RestockVehicle.class);
		add(Scan.class);
		add(Scanned.class);
		add(ScientificResearch.class);
		add(Screenshot.class);
		add(SelfDestruct.class);
		add(SellDrones.class);
		add(SellExplorationData.class);
		add(SendText.class);
		add(SetUserShipName.class);
		add(ShieldState.class);
		add(ShipyardBuy.class);
		add(ShipyardNew.class);
		add(ShipyardSell.class);
		add(ShipyardTransfer.class);
		add(ShipyardSwap.class);
		add(StartJump.class);
		add(SupercruiseEntry.class);
		add(SupercruiseExit.class);
		add(Synthesis.class);
		add(Touchdown.class);
		add(Undocked.class);
		add(USSDrop.class);
		add(VehicleSwitch.class);
		add(WingAdd.class);
		add(WingJoin.class);
		add(WingLeave.class);
	}

	@Override
	public JournalEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
		JsonObject obj = json.getAsJsonObject();
		String eventName = obj.get("event").getAsString();
		
		if (JournalEventDeserializer.eventClasses.containsKey(eventName)) {
			return ctx.deserialize(json, JournalEventDeserializer.eventClasses.get(eventName));
		} else {
			log.warn("Unrecognized journal event {}", eventName);
			log.debug("Unrecognized event JSON: {}", json.toString());
			return ctx.deserialize(json, UnknownJournalEvent.class);
		}
	}
}
