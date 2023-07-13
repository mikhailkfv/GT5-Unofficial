package gregtech.api.interfaces.internal;

import net.minecraftforge.fluids.Fluid;

public interface GasesCompat {

	public boolean registerGregGas(Fluid gas);
	
	public boolean registerOtherGasesAsFluids();
	
	public boolean registerFluidCannerRecipes();
}
