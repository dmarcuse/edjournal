package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.*;

/**
 * Represents a targeted scan of a stellar body. For convenience, this class is
 * only an abstract superclass for more detailed classes depending on the scan.
 * 
 * @see StarScan
 * @see BasicBodyScan
 * @see DetailedBodyScan
 * 
 * @author apemanzilla
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public abstract class Scan extends JournalEvent {
	private String bodyName;
	private double distanceFromArrivalLS;

	@Nullable
	private Double semiMajorAxis;

	@Nullable
	private Double eccentricity;

	@Nullable
	private Double orbitalInclination;

	@Nullable
	private Double periapsis;

	@Nullable
	private Double orbitalPeriod;
	private List<Ring> rings = Collections.emptyList();

	/**
	 * @return Whether this scan is a scan of a star or of a body.
	 * @see #isDetailed()
	 * @see StarScan
	 * @see BasicBodyScan
	 * @see DetailedBodyScan
	 */
	public abstract boolean isStar();

	/**
	 * @return Whether this scan is a detailed body scan or not. Note that star
	 *         scans will always return <code>false</code> regardless of whether
	 *         a detailed surface scanner was used.
	 * 
	 * @see #isStar()
	 * @see StarScan
	 * @see BasicBodyScan
	 * @see DetailedBodyScan
	 */
	public abstract boolean isDetailed();

	/**
	 * Represents a scan of a star or star-type object (e.g. black hole)
	 * 
	 * @author apemanzilla
	 *
	 */
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	@Getter
	public static class StarScan extends Scan {
		public boolean isStar() {
			return true;
		}

		public boolean isDetailed() {
			return false;
		}

		private StarClass starType;
		private double stellarMass;
		private double radius;
		private double absoluteMagnitude;
		private double rotationPeriod;
		private double surfaceTemperature;
		private double age_MY;
	}

	/**
	 * Represents a basic scan of a planet or moon (no detailed surface scanner
	 * used)<br>
	 * <br>
	 * Because {@link DetailedBodyScan} is a child of this class, you should use
	 * {@link Scan#isDetailed()} to determine whether a scan is a detailed scan
	 * or not, rather than <code>instanceof</code>.
	 * 
	 * @author apemanzilla
	 *
	 */
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	@Getter
	public static class BasicBodyScan extends Scan {
		public boolean isStar() {
			return false;
		}

		public boolean isDetailed() {
			return false;
		}

		private String planetClass;
		private double surfaceGravity;
		private double radius;
		private double massEM;
		private boolean landable = false;

		@Nullable
		private Double rotationPeriod;

		@Nullable
		private Double axialTilt;
	}

	/**
	 * Represents a detailed scan of a planet or moon (detailed surface scanner
	 * used)
	 * 
	 * @author apemanzilla
	 *
	 */
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	@Getter
	public static class DetailedBodyScan extends BasicBodyScan {
		public boolean isDetailed() {
			return true;
		}

		private boolean tidalLock;
		private String terraformState;
		private String atmosphere;
		private String atmosphereType;
		private List<AtmosphereComponent> atmosphereComposition;
		private String volcanism;
		private double surfaceTemperature;
		private double surfacePressure;
		private List<MaterialPercentage> materials = Collections.emptyList();
		private ReserveType reserveLevel = ReserveType.None;
	}
}
