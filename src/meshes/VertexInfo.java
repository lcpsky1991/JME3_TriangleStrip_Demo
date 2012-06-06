package meshes;

import com.jme3.math.Vector3f;

/**
 * Saves the information about a single vertex.
 * 
 * @author Saman Sedighi Rad
 * 
 */
public class VertexInfo {

	public Vector3f vector;
	public Vector3f normal;

	public VertexInfo(Vector3f vector, Vector3f normal) {
		this.vector = vector;
		this.normal = normal;
	}

	@Override
	public String toString() {
		return "(vector(vertex), normal(vertex)) -> " + "(" + this.vector + ", "	+ this.normal + ")";
	}

}
