package geometries;

import meshes.MeshParameters;
import meshes.TrianglePlane;
import meshes.types.MeshTypes;

import com.jme3.math.FastMath;

/**
 * Geometry build from the {@link TrianglePlane}.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class GTrianglePlane extends AGeometry {
	
	public GTrianglePlane(MeshParameters parameters) {
		super("Plane", MeshTypes.Plane, parameters);		
		this.name = "Plane";
		this.mesh = new TrianglePlane(parameters.x_count, parameters.y_count);
		this.rotate(0f, FastMath.PI, 0f);
	}

}
