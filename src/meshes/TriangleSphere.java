package meshes;

import java.util.ArrayList;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 * This mesh is a single triangle mesh drawing a complete sphere from
 * the south pole to the north pole.
 * 
 * @author Saman Sedighi Rad
 *
 */
public class TriangleSphere extends AMesh {
	
	public TriangleSphere(int x_count, int y_count, float diameter) {
		super(x_count, y_count, diameter);		
	}

	@Override
	protected void create() {

		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		
		float current_diameter = 0f;
		float current_radius = this.radius;
		float radius_on_angle_to_top = 0f;
		float angle_on_circle_to_top = 0f;
		float delta_to_top = this.diameter / this.y_count;
		
		for (int diameter_step = 0; diameter_step <= this.y_count; diameter_step += 1) {
			
			// Rotate around a circle looking from direction +y to -y
			float current_circumference = (2 * FastMath.PI * radius_on_angle_to_top);
			
			float circle_angle_steps = 0f;
			if (radius_on_angle_to_top > 0f) {
				circle_angle_steps = (current_circumference / this.x_count) / radius_on_angle_to_top;
			}
				
			float alpha = 0f;
			float alpha_next = 0f;
			float next_circle_angle_steps = 0f;
			
			for (float i = 0; i < this.x_count; i++) {
				alpha += circle_angle_steps;

				float circle_y = current_diameter;
				float circle_x = 0f; 
				float circle_z = 0f;
				
				if (circle_angle_steps > 0f) {
					circle_x = radius_on_angle_to_top * FastMath.cos(alpha);
					circle_z = radius_on_angle_to_top * FastMath.sin(alpha);
				}
				Vector3f current_vertex = new Vector3f(circle_x, circle_y, circle_z);
				vertices.add(current_vertex);
				
				// Attach vertex information
				this.vertexInfos.add(new VertexInfo(current_vertex.clone(), current_vertex.clone().normalizeLocal()));
				
				// Connect with the point on next higher circle in +y direction					
				// We need to pre-compute the next point...				
				float next_diameter = current_diameter + delta_to_top;
				float next_radius = FastMath.abs(this.radius - next_diameter);					
				float next_angle_on_circle_to_top = FastMath.acos(next_radius / this.radius);
				float next_radius_on_angle_to_top = this.radius * FastMath.sin(next_angle_on_circle_to_top);
				
				if (next_radius_on_angle_to_top > 0f) {
					float next_circumference = (2 * FastMath.PI * next_radius_on_angle_to_top);
					next_circle_angle_steps = (next_circumference / this.x_count) / next_radius_on_angle_to_top;
				}
				alpha_next += next_circle_angle_steps;
				
				float next_circle_y = next_diameter;
				float next_circle_x = next_radius_on_angle_to_top * FastMath.cos(alpha_next);
				float next_circle_z = next_radius_on_angle_to_top * FastMath.sin(alpha_next);
				
				Vector3f next_vertex = new Vector3f(next_circle_x, next_circle_y, next_circle_z);
				vertices.add(next_vertex);
				
				// Attach vertex information
				this.vertexInfos.add(new VertexInfo(next_vertex.clone(), next_vertex.clone().normalizeLocal()));
			}

			// Vertical half circle to the top
			current_diameter += delta_to_top;
			current_radius = FastMath.abs(this.radius - current_diameter);
			angle_on_circle_to_top = FastMath.acos(current_radius / this.radius);
			radius_on_angle_to_top = this.radius * FastMath.sin(angle_on_circle_to_top);
		}

		this.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(this.getArrayListAsVector3fArray(vertices)));
		this.updateBound();

	}

}
