package main;

import geometries.AGeometry;
import geometries.GTrianglePlane;
import geometries.GTriangleSphere;

import java.util.logging.Level;

import meshes.MeshParameters;
import meshes.types.MeshTypes;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

/**
 * JMonkeyEngine 3 {@link SimpleApplication} implementation.
 * This class also implements {@link InputListener} for the key controls.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class P02Application extends SimpleApplication implements InputListener {
	
	AGeometry g;
	
	// Default values
	
	private static float total_scale = 5f;
	private static int x_count = 20;
	private static int y_count = 10;
	private static float radius = 1f;

	@Override
	public void simpleInitApp() {
    	java.util.logging.Logger.getLogger("").setLevel(Level.SEVERE);

		g = new GTrianglePlane(new MeshParameters(x_count, y_count));
		g.scale(total_scale);
		rootNode.attachChild(g);

		this.initKeys();
        
        getCamera().lookAt(g.center().getLocalTranslation(), Vector3f.UNIT_Y);     
	}
	
	@Override
	public void simpleUpdate(float tpf) {
		super.simpleUpdate(tpf);	
		
		if (g.meshType.equals(MeshTypes.Sphere)) {
			g.rotate(0f, FastMath.PI / 12 * tpf, 0f);
		}
		
		guiNode.detachAllChildren();
		getHudText();
	}
	
	private void getHudText() {
		BitmapText hudText = new BitmapText(guiFont, false);
		
		hudText.setSize(guiFont.getCharSet().getRenderedSize()); // font size
		hudText.setColor(ColorRGBA.White); // font color
		
		String text = g.getName()
					+ "\nX: " + x_count + " [1+,2-]"
					+ "\nY: " + y_count + " [3+,4-]"
					+ "\nScale: " + total_scale + " [5+,6-]";
		
		if (g.meshType.equals(MeshTypes.Sphere)) {
			text += "\nRadius: "  + radius + " [7+,8-]";
		}
		
		text += "\nPlane/Sphere [9,0]";
		
		hudText.setText(text);		
		
		hudText.setLocalTranslation(5, hudText.getLineHeight()+110, 0); // position
		guiNode.attachChild(hudText);
	}

	private void initKeys() {

		inputManager.addMapping("scale-up", new KeyTrigger(KeyInput.KEY_5));
		inputManager.addMapping("scale-down", new KeyTrigger(KeyInput.KEY_6));
		
		inputManager.addMapping("plus-x", new KeyTrigger(KeyInput.KEY_1));
		inputManager.addMapping("minus-x", new KeyTrigger(KeyInput.KEY_2));
		
		inputManager.addMapping("plus-y", new KeyTrigger(KeyInput.KEY_3));
		inputManager.addMapping("minus-y", new KeyTrigger(KeyInput.KEY_4));

		inputManager.addMapping("radius-plus", new KeyTrigger(KeyInput.KEY_7));
		inputManager.addMapping("radius-minus", new KeyTrigger(KeyInput.KEY_8));
		
		inputManager.addMapping("Plane", new KeyTrigger(KeyInput.KEY_9));
		inputManager.addMapping("Sphere", new KeyTrigger(KeyInput.KEY_0));
		
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Top", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Bottom", new KeyTrigger(KeyInput.KEY_S));
				
		// Add the names to the action listener.
		inputManager.addListener(actionListener, new String[] { "scale-up", "scale-down", "plus-x", "minus-x", "plus-y", "minus-y", "Plane", "Sphere", "radius-plus", "radius-minus" });
		inputManager.addListener(analogListener, new String[] { "Left", "Right", "Front", "Back" });
	}

	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			
			float scale = 0f;
			
			// scale
			if (name.equals("scale-up") && !keyPressed) {				
				scale += 1.1f;
				g.scale(scale);	

				total_scale += scale - 1f;
			}
			if (name.equals("scale-down") && !keyPressed) {
				scale = 0.9f;
				g.scale(scale);
				total_scale += scale - 1f;
			}
			
			// X
			if (name.equals("plus-x") && !keyPressed) {
				x_count += 1;
				create();
			}
			if (name.equals("minus-x") && !keyPressed) {
				x_count -= 1;

				if (x_count < 2) {
					x_count = 2;
				}
				create();
			}
			
			// Y
			if (name.equals("plus-y") && !keyPressed) {
				y_count += 1;
				create();
			}
			if (name.equals("minus-y") && !keyPressed) {
				y_count -= 1;

				if (y_count < 2) {
					y_count = 2;
				}
				create();
			}
			
			// Radius
			if (name.equals("radius-minus") && !keyPressed && g.meshType.equals(MeshTypes.Sphere)) {
				radius -= 0.1f;
				if (radius < 0.1f) {
					radius = 0.1f;
				}
				create();
			}
			if (name.equals("radius-plus") && !keyPressed && g.meshType.equals(MeshTypes.Sphere)) {
				radius += 0.1;
				create();
			}
			
			// Switch geometry
			if (name.equals("Plane") && !keyPressed && !g.meshType.equals(MeshTypes.Plane)) {
				rootNode.detachChild(g);
				
				g = new GTrianglePlane(new MeshParameters(x_count, y_count));
				g.scale(total_scale);
				
				rootNode.attachChild(g);
			}
			if (name.equals("Sphere") && !keyPressed && !g.meshType.equals(MeshTypes.Sphere)) {
				rootNode.detachChild(g);
				
				g = new GTriangleSphere(new MeshParameters(x_count, y_count, radius*2f));
				g.scale(total_scale);
				
				rootNode.attachChild(g);
			}
			
		}
	};
	
	private AnalogListener analogListener = new AnalogListener() {
		
		public void onAnalog(String name, float value, float tpf) {
			
			float delta_speed = 4f;
			
			if (name.equals("Right")) {
				Vector3f v = getCamera().getLocation();
				getCamera().setLocation(new Vector3f(v.x + value * speed * delta_speed, v.y, v.z));
			}
			if (name.equals("Left")) {
				Vector3f v = getCamera().getLocation();
				getCamera().setLocation(new Vector3f(v.x - value * speed * delta_speed, v.y, v.z));
			}
			if (name.equals("Top")) {
				Vector3f v = getCamera().getLocation();
				getCamera().setLocation(new Vector3f(v.x, v.y + value * speed * delta_speed * 8, v.z));
			}
			if (name.equals("Bottom")) {
				Vector3f v = getCamera().getLocation();
				getCamera().setLocation(new Vector3f(v.x, v.y - value * speed * delta_speed * 8, v.z));
			}
			
		}
		
	};
	
	/**
	 * Recreates the current geometry.
	 * @return
	 */
	protected void create() {
		rootNode.detachChild(g);
		
		if (g.meshType == MeshTypes.Plane) {
			g = new GTrianglePlane(new MeshParameters(x_count, y_count));
		}
		else if (g.meshType == MeshTypes.Sphere) {
			g = new GTriangleSphere(new MeshParameters(x_count, y_count, radius * 2f));
		}
		
		g.scale(total_scale);
		rootNode.attachChild(g);
	}
	
}
