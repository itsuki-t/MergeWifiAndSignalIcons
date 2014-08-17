package jp.rmitkt.xposed.mwasi;

import android.content.res.Resources;
import android.content.res.XModuleResources;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class mwasi implements IXposedHookZygoteInit,IXposedHookInitPackageResources {
	private static final String CLASSNAME_SYSTEMUI = "com.android.systemui";
	private static String modulePath = null;
	private static XModuleResources modRes;
	private static String VERSION_CODES = null;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
		if(Build.VERSION.SDK_INT >= 19){
			VERSION_CODES = "KK";
		}else if(Build.VERSION.SDK_INT >= 16){
			VERSION_CODES = "JB";
		}
	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		if (!resparam.packageName.equals(CLASSNAME_SYSTEMUI)) {
			return;
		}
		modRes = XModuleResources.createInstance(modulePath, resparam.res);

		// sort layout
	    resparam.res.hookLayout(CLASSNAME_SYSTEMUI, "layout", "signal_cluster_view", new XC_LayoutInflated() {
	        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
	        	ViewGroup vg = (ViewGroup)liparam.view;
	        	FrameLayout wasIconLayout = (FrameLayout)liparam.view.findViewById(liparam.res.getIdentifier("wifi_combo", "id", CLASSNAME_SYSTEMUI));
	        	vg.removeView(wasIconLayout);
	        	vg.addView(wasIconLayout);
	        }
	    });

		// add or replace resources For KK(Sony Only?)
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_in", R.drawable.somc_sys_wifi_in);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_inout", R.drawable.somc_sys_wifi_inout);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_out", R.drawable.somc_sys_wifi_out);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_limited_signal_0", R.drawable.somc_sys_wifi_limited_signal_0);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_limited_signal_1", R.drawable.somc_sys_wifi_limited_signal_1);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_limited_signal_2", R.drawable.somc_sys_wifi_limited_signal_2);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_limited_signal_3", R.drawable.somc_sys_wifi_limited_signal_3);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_limited_signal_4", R.drawable.somc_sys_wifi_limited_signal_4);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_0", R.drawable.somc_sys_wifi_signal_0);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_1", R.drawable.somc_sys_wifi_signal_1);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_1_fully", R.drawable.somc_sys_wifi_signal_1_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_2", R.drawable.somc_sys_wifi_signal_2);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_2_fully", R.drawable.somc_sys_wifi_signal_2_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_3", R.drawable.somc_sys_wifi_signal_3);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_3_fully", R.drawable.somc_sys_wifi_signal_3_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_4", R.drawable.somc_sys_wifi_signal_4);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_4_fully", R.drawable.somc_sys_wifi_signal_4_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "somc_sys_wifi_signal_null", R.drawable.somc_sys_wifi_signal_null);						

		// add or replace resources For JB and KK
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_in", R.drawable.stat_sys_wifi_in);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_inout", R.drawable.stat_sys_wifi_inout);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_out", R.drawable.stat_sys_wifi_out);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_0", R.drawable.stat_sys_wifi_limited_signal_0);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_1", R.drawable.stat_sys_wifi_limited_signal_1);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_2", R.drawable.stat_sys_wifi_limited_signal_2);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_3", R.drawable.stat_sys_wifi_limited_signal_3);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_limited_signal_4", R.drawable.stat_sys_wifi_limited_signal_4);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_0", R.drawable.stat_sys_wifi_signal_0);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_1", R.drawable.stat_sys_wifi_signal_1);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_1_fully", R.drawable.stat_sys_wifi_signal_1_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_2", R.drawable.stat_sys_wifi_signal_2);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_2_fully", R.drawable.stat_sys_wifi_signal_2_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_3", R.drawable.stat_sys_wifi_signal_3);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_3_fully", R.drawable.stat_sys_wifi_signal_3_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_4", R.drawable.stat_sys_wifi_signal_4);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_4_fully", R.drawable.stat_sys_wifi_signal_4_fully);
		editResource(resparam, CLASSNAME_SYSTEMUI, "drawable", "stat_sys_wifi_signal_null", R.drawable.stat_sys_wifi_signal_null);
	}
	
	 private void editResource(InitPackageResourcesParam resparam, String pkg, String type, String name, int id) {
		 Resources res = XModuleResources.createInstance(modulePath, resparam.res);
		 if((resparam.res.getIdentifier(name, "drawable", resparam.packageName)) != 0){
			 resparam.res.setReplacement(pkg, type, name, modRes.fwd(id));
		 }else{
			 resparam.res.addResource(res,id);
		 }
	 }
}
