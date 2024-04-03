package dev.ftb.mods.ftbskiescompanion;

import com.simibubi.create.foundation.utility.Couple;

public record StressInfo(double impact, double capacity, boolean hasImpact, boolean hasCapacity, Couple<Integer> generatedRPM) {
}
