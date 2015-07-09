package the_fireplace.clayspawn.firecorecompat;

public class FCCompatDummy implements IFCCompat {

	@Override
	public void register() {
		System.out.println("Fire's Clay Spawwn version checker disabled due to missing Fireplace Core.");
	}

}
