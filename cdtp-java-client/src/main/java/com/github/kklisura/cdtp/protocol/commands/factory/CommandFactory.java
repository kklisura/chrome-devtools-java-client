package com.github.kklisura.cdtp.protocol.commands.factory;

import com.github.kklisura.cdtp.protocol.commands.Accessibility;
import com.github.kklisura.cdtp.protocol.commands.Animation;
import com.github.kklisura.cdtp.protocol.commands.ApplicationCache;
import com.github.kklisura.cdtp.protocol.commands.Audits;
import com.github.kklisura.cdtp.protocol.commands.Browser;
import com.github.kklisura.cdtp.protocol.commands.CSS;
import com.github.kklisura.cdtp.protocol.commands.CacheStorage;
import com.github.kklisura.cdtp.protocol.commands.Console;
import com.github.kklisura.cdtp.protocol.commands.DOM;
import com.github.kklisura.cdtp.protocol.commands.DOMDebugger;
import com.github.kklisura.cdtp.protocol.commands.DOMSnapshot;
import com.github.kklisura.cdtp.protocol.commands.DOMStorage;
import com.github.kklisura.cdtp.protocol.commands.Database;
import com.github.kklisura.cdtp.protocol.commands.Debugger;
import com.github.kklisura.cdtp.protocol.commands.DeviceOrientation;
import com.github.kklisura.cdtp.protocol.commands.Emulation;
import com.github.kklisura.cdtp.protocol.commands.HeapProfiler;
import com.github.kklisura.cdtp.protocol.commands.IO;
import com.github.kklisura.cdtp.protocol.commands.IndexedDB;
import com.github.kklisura.cdtp.protocol.commands.Input;
import com.github.kklisura.cdtp.protocol.commands.Inspector;
import com.github.kklisura.cdtp.protocol.commands.LayerTree;
import com.github.kklisura.cdtp.protocol.commands.Log;
import com.github.kklisura.cdtp.protocol.commands.Memory;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.protocol.commands.Overlay;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.protocol.commands.Performance;
import com.github.kklisura.cdtp.protocol.commands.Profiler;
import com.github.kklisura.cdtp.protocol.commands.Runtime;
import com.github.kklisura.cdtp.protocol.commands.Schema;
import com.github.kklisura.cdtp.protocol.commands.Security;
import com.github.kklisura.cdtp.protocol.commands.ServiceWorker;
import com.github.kklisura.cdtp.protocol.commands.Storage;
import com.github.kklisura.cdtp.protocol.commands.SystemInfo;
import com.github.kklisura.cdtp.protocol.commands.Target;
import com.github.kklisura.cdtp.protocol.commands.Tethering;
import com.github.kklisura.cdtp.protocol.commands.Tracing;

public interface CommandFactory {

	/**
	 * Returns the Inspector command.
	 */
	Inspector getInspector();

	/**
	 * Returns the Memory command.
	 */
	Memory getMemory();

	/**
	 * Returns the Performance command.
	 */
	Performance getPerformance();

	/**
	 * Returns the Page command.
	 */
	Page getPage();

	/**
	 * Returns the Overlay command.
	 */
	Overlay getOverlay();

	/**
	 * Returns the Emulation command.
	 */
	Emulation getEmulation();

	/**
	 * Returns the Security command.
	 */
	Security getSecurity();

	/**
	 * Returns the Audits command.
	 */
	Audits getAudits();

	/**
	 * Returns the Network command.
	 */
	Network getNetwork();

	/**
	 * Returns the Database command.
	 */
	Database getDatabase();

	/**
	 * Returns the IndexedDB command.
	 */
	IndexedDB getIndexedDB();

	/**
	 * Returns the CacheStorage command.
	 */
	CacheStorage getCacheStorage();

	/**
	 * Returns the DOMStorage command.
	 */
	DOMStorage getDOMStorage();

	/**
	 * Returns the ApplicationCache command.
	 */
	ApplicationCache getApplicationCache();

	/**
	 * Returns the DOM command.
	 */
	DOM getDOM();

	/**
	 * Returns the CSS command.
	 */
	CSS getCSS();

	/**
	 * Returns the DOMSnapshot command.
	 */
	DOMSnapshot getDOMSnapshot();

	/**
	 * Returns the IO command.
	 */
	IO getIO();

	/**
	 * Returns the DOMDebugger command.
	 */
	DOMDebugger getDOMDebugger();

	/**
	 * Returns the Target command.
	 */
	Target getTarget();

	/**
	 * Returns the ServiceWorker command.
	 */
	ServiceWorker getServiceWorker();

	/**
	 * Returns the Input command.
	 */
	Input getInput();

	/**
	 * Returns the LayerTree command.
	 */
	LayerTree getLayerTree();

	/**
	 * Returns the DeviceOrientation command.
	 */
	DeviceOrientation getDeviceOrientation();

	/**
	 * Returns the Tracing command.
	 */
	Tracing getTracing();

	/**
	 * Returns the Animation command.
	 */
	Animation getAnimation();

	/**
	 * Returns the Accessibility command.
	 */
	Accessibility getAccessibility();

	/**
	 * Returns the Storage command.
	 */
	Storage getStorage();

	/**
	 * Returns the Log command.
	 */
	Log getLog();

	/**
	 * Returns the SystemInfo command.
	 */
	SystemInfo getSystemInfo();

	/**
	 * Returns the Tethering command.
	 */
	Tethering getTethering();

	/**
	 * Returns the Browser command.
	 */
	Browser getBrowser();

	/**
	 * Returns the Schema command.
	 */
	Schema getSchema();

	/**
	 * Returns the Runtime command.
	 */
	Runtime getRuntime();

	/**
	 * Returns the Debugger command.
	 */
	Debugger getDebugger();

	/**
	 * Returns the Console command.
	 */
	Console getConsole();

	/**
	 * Returns the Profiler command.
	 */
	Profiler getProfiler();

	/**
	 * Returns the HeapProfiler command.
	 */
	HeapProfiler getHeapProfiler();
}
