package jp.rmitkt.xposed;

import jp.rm.xposed.mwi.R;
import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class mwi implements IXposedHookZygoteInit,
IXposedHookInitPackageResources {
	public static final String CLASSNAME_SYSTEMUI = "com.android.systemui";
	private static String modulePath = null;
	public static XModuleResources modRes;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		if (!resparam.packageName.equals(CLASSNAME_SYSTEMUI)) {
			return;
		}
		modRes = XModuleResources.createInstance(modulePath, resparam.res);
		
		// replace layout
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "layout", "signal_cluster_view", modRes.fwd(R.layout.cust_signal_cluster_view));

		// add resource
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_in);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_inout);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_limited_signal_0);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_limited_signal_1);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_limited_signal_2);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_limited_signal_3);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_limited_signal_4);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_out);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_0);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_1);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_1_fully);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_2);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_2_fully);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_3);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_3_fully);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_4);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_4_fully);
		resparam.res.addResource(modRes, R.drawable.somc_sys_wifi_signal_null);

		// replace resource
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_in", modRes.fwd(R.drawable.stat_sys_wifi_in));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_inout", modRes.fwd(R.drawable.stat_sys_wifi_inout));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_out", modRes.fwd(R.drawable.stat_sys_wifi_out));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_0", modRes.fwd(R.drawable.stat_sys_wifi_limited_signal_0));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_1", modRes.fwd(R.drawable.stat_sys_wifi_limited_signal_1));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_2", modRes.fwd(R.drawable.stat_sys_wifi_limited_signal_2));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_3", modRes.fwd(R.drawable.stat_sys_wifi_limited_signal_3));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_4", modRes.fwd(R.drawable.stat_sys_wifi_limited_signal_4));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_0", modRes.fwd(R.drawable.stat_sys_wifi_signal_0));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_1", modRes.fwd(R.drawable.stat_sys_wifi_signal_1));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_1_fully", modRes.fwd(R.drawable.stat_sys_wifi_signal_1_fully));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_2", modRes.fwd(R.drawable.stat_sys_wifi_signal_2));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_2_fully", modRes.fwd(R.drawable.stat_sys_wifi_signal_2_fully));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_3", modRes.fwd(R.drawable.stat_sys_wifi_signal_3));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_3_fully", modRes.fwd(R.drawable.stat_sys_wifi_signal_3_fully));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_4", modRes.fwd(R.drawable.stat_sys_wifi_signal_4));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_4_fully", modRes.fwd(R.drawable.stat_sys_wifi_signal_4_fully));
		resparam.res.setReplacement(CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_null", modRes.fwd(R.drawable.stat_sys_wifi_signal_null));
	}
}
