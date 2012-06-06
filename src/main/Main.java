package main;
import com.jme3.system.AppSettings;

/**
 * Main class to launch the application.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class Main {

	private static P02Application app;

	public static void main(String[] args) {
		AppSettings settings = new AppSettings(true);
		
		settings.setResolution(1024, 768);
		settings.setSamples(16);
		settings.setTitle("Saman Sedighi Rad - P02");

		app = new P02Application();

		app.setSettings(settings);
		app.setShowSettings(false);

		app.start();
	}
	
	public static P02Application getInstance() {
		if (app == null) {
			app = new P02Application();
		}
		return app;
	}

}
