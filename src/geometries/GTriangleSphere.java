package geometries;

import meshes.MeshParameters;
import meshes.TrianglePlane;
import meshes.TriangleSphere;
import meshes.types.MeshTypes;

/**
 * Geometry build from the {@link TrianglePlane}.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class GTriangleSphere extends AGeometry {
	
	public GTriangleSphere(MeshParameters parameters) {
		super("Sphere", MeshTypes.Sphere, parameters);		
		this.mesh = new TriangleSphere(parameters.x_count, parameters.y_count, parameters.diameter);
	}
	
}
