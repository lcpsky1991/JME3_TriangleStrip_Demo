package geometries;

import main.Main;
import meshes.MeshParameters;
import meshes.types.MeshTypes;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;

/**
 * Container class for all demonstrated geometries within the application.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class AGeometry extends Geometry {
	
	public MeshParameters parameters;
	public MeshTypes meshType;

	public AGeometry(String name, MeshTypes meshType, MeshParameters parameters) {
		this.name = name;
		this.meshType = meshType;
		this.parameters = parameters;
		
		Material mat_wire = new Material(Main.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");

		mat_wire.setColor("Color", ColorRGBA.Red);
		mat_wire.getAdditionalRenderState().setWireframe(true);

		this.setMaterial(mat_wire);
	}

}
