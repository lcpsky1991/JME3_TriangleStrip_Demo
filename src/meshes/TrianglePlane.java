package meshes;

import java.util.ArrayList;

import com.jme3.math.Vector3f;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 * This mesh is a plane created from a single triangle fan
 * starting from bottom-left to the top-right.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class TrianglePlane extends AMesh {
		
	public TrianglePlane(int x_count, int y_count) {
		super(x_count, y_count);
	}
	
	@Override
	protected void create() {

		float x_subdivisions = 1f / (float) x_count;
    	float y_subdivisions = 1f / (float) y_count;

        ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
        
        // y - 1 because we set always the current x vertex and an additional y point at once.
        float y = 0f;
        for (int y_counter = 0; y_counter < y_count-1; y_counter += 1) {
        	 y += y_subdivisions;
        	
        	float x = 0f;
	        for (int x_counter = 0; x_counter < x_count; x_counter += 1) {
	        	x += x_subdivisions;
	        	
	        	// The point itself at the current subdivision
	        	Vector3f v = new Vector3f(x, y, 0);
	        	vertices.add(v);
	        	
	        	// Add vertex information
	        	this.vertexInfos.add(new VertexInfo(v.clone(), v.clone().normalizeLocal()));
	        	
	        	// The point above the current point
	        	Vector3f v2 = new Vector3f(x, y + y_subdivisions, 0);
	        		        	
	        	vertices.add(v2);
	        	this.vertexInfos.add(new VertexInfo(v2.clone(), v2.clone().normalizeLocal()));
			}   
        }
		
        this.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(this.getArrayListAsVector3fArray(vertices)));
        this.updateBound();		
	}

}
