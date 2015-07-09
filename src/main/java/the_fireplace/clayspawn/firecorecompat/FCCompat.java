package the_fireplace.clayspawn.firecorecompat;

import the_fireplace.clayspawn.ClaySpawn;
import the_fireplace.fireplacecore.api.FCAPI;
import the_fireplace.fireplacecore.math.VersionMath;
/**
 *
 * @author The_Fireplace
 *
 */
public class FCCompat implements IFCCompat {

	@Override
	public void register() {
		FCAPI.registerModToVersionChecker(ClaySpawn.MODNAME, ClaySpawn.VERSION, VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/f78akvw8ugw52ye/prerelease.version?dl=0"), VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/p8r9sxhab98xqb5/release.version?dl=0"), ClaySpawn.downloadURL, ClaySpawn.MODID);
	}

}
