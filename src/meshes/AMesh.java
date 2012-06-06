package meshes;

import java.util.ArrayList;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;

/**
 * Common class for all meshes which share properties.
 * 
 * @author Saman Sedighi Rad
 *
 */
public abstract class AMesh extends Mesh {

	protected int x_count;
	protected int y_count;
	
	protected float radius;
	protected float diameter;
		
	/**
	 * Part of our task was to keep information
	 * about all vertices and their normals.
	 */
	protected ArrayList<VertexInfo> vertexInfos;
	
	/** Default mesh type for all sub classes. */
	protected final Mesh.Mode MESH_MODE = Mesh.Mode.TriangleStrip;
	
	/**
	 * Constructor for planes.
	 * @param x_count Number of subdivisions/segments in x direction.
	 * @param y_count Number of subdivisions/segments in y direction.
	 */
	protected AMesh(int x_count, int y_count) {
		this.x_count = x_count;
		this.y_count = y_count;
		
		this.vertexInfos = new ArrayList<VertexInfo>();
		
		this.setMode(MESH_MODE);
		this.create();
	}
	
	/**
	 * Constructor for planes.
	 * @param x_count Number of subdivisions/segments in x direction.
	 * @param y_count Number of subdivisions/segments in y direction.
	 * @param diameter Diameter of spehre meshes.
	 */
	protected AMesh(int x_count, int y_count, float diameter) {
		this.diameter = diameter;
		this.radius = diameter / 2f;
		
		this.x_count = x_count;
		this.y_count = y_count;
		
		this.vertexInfos = new ArrayList<VertexInfo>();
		
		this.setMode(MESH_MODE);
		this.create();
	}

	/**
	 * Creates the actual mesh.
	 */
	protected abstract void create();

	/**
	 * Returns the information about all vertices, means position and normal.
	 * @return
	 */
	protected ArrayList<VertexInfo> getVertexInfos() {
		return this.vertexInfos;
	}
	
	/**
	 * Converts an {@link ArrayList} to a plan array for the jmonkey mesh generator.
	 * @param vertices Array list of vertices.
	 * @return 1d-array with the vertices.
	 */
	protected Vector3f[] getArrayListAsVector3fArray(ArrayList<Vector3f> vertices) {
		Vector3f[] array = new Vector3f[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			array[i] = vertices.get(i);
		}
		
		return array;
	}
	
}
