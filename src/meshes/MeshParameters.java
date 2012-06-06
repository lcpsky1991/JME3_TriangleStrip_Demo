package meshes;

/**
 * Holds the paramaters for the mesh generation.
 * 
 * @author Saman Sedighi Rad
 * 
 */
public class MeshParameters {

	public int x_count;
	public int y_count;
	public float diameter;
	public float radius;

	public MeshParameters(int x_count, int y_count) {
		this.x_count = x_count;
		this.y_count = y_count;
	}

	public MeshParameters(int x_count, int y_count, float diameter) {
		this.x_count = x_count;
		this.y_count = y_count;

		this.diameter = diameter;
		this.radius = diameter / 2f;
	}

}
