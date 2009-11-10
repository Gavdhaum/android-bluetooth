package it.gerdavax.bluetooth;

import it.gerdavax.android.bluetooth.util.PlatformChecker;
import android.content.Context;

public abstract class LocalDevice {
	protected Context ctx;
	protected ScanListener scanListener = null;
	private static final int SDK_NUM_2_0 = 5;
	public static LocalDevice getInstance() {
		LocalDevice toRet = null;
		int vInt = PlatformChecker.getVersionNumber();
		if (vInt < SDK_NUM_2_0) {
			toRet = new it.gerdavax.bluetooth.android1.LocalDevice1Impl();
		} else {
			toRet = new it.gerdavax.bluetooth.android2.LocalDevice2Impl();
		}
		return toRet;
	}

	public final void init(final Context _ctx)  throws Exception {
		ctx = _ctx;
		doInit();
	}

	public final void destroy() {
		doDestroy();
		ctx = null;
	}


	public final void scan(final ScanListener listener)  throws Exception{
		scanListener = listener;
		doScan();
	}

	protected abstract void doInit() throws Exception;

	protected abstract void doScan() throws Exception;
	
	protected abstract void doDestroy();
}