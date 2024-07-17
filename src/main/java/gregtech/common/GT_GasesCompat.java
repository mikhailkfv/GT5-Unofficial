package gregtech.common;
import gregtech.api.interfaces.internal.IGasesCompat;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.HashMap;

import glenn.gasesframework.api.Combustibility;
import glenn.gasesframework.api.GFAPI;
import glenn.gasesframework.api.gastype.GasType;

public class GT_GasesCompat implements IGasesCompat {
	
	private final HashMap<GasType, Fluid> _fluidMap = new HashMap<GasType, Fluid>();

	@Override
	public boolean registerGregGas(Fluid gas) {
		String name = gas.getName();
		int id = gas.getID() + 19; //Glenn's gases uses 18 IDs so we can safely start at 19
		int color = gas.getColor();
		int density = gas.getDensity();
		//TODO: introduction of per-gas properties not in fluid data
		GasType gt = new GasType(false, id, name, color, 0, density, Combustibility.NONE);
		GFAPI.registry.registerGasType(gt);
		return true;
	}

	@Override
	public boolean registerOtherGasesAsFluids() {
		for(int i = 0; i < 18; i++) {
			GasType gt = GFAPI.registry.getGasTypeByID(i);
			Fluid f = gregOrGenerateFluid(gt);
			_fluidMap.put(gt, f);
		}
		return false;
	}

	@Override
	public boolean registerFluidCannerRecipes() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Fluid getFluidFromGas(Object gas)
	{
		GasType gt = (GasType) gas;
		return _fluidMap.get(gt);
	}
	
	public Fluid gregOrGenerateFluid(GasType gas)
	{
		switch (gas.gasID) {
			case 0:
				return null;
			case 1:
				return null;
			case 2:
				return FluidRegistry.getFluid("Steam");
			case 3:
				return FluidRegistry.getFluid("gas_natural_gas");
			case 7:
				return FluidRegistry.getFluid("liquid_hydricsulfur");
			case 8:
				return FluidRegistry.getFluid("NitrogenDioxide");
			case 13:
				return FluidRegistry.getFluid("Chlorine");
			default:
				return null; //TODO: Auto-generate gas with config options for customization
		}
	}
}
