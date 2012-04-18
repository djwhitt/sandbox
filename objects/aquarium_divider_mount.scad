union() {
	difference() {
		cube(size=[70,20,60]);
	
		translate(v=[-5,2,2]) cube(size=[80,20,60]);
	
		translate(v=[0,-5,2])
		rotate(a=[0,25,0])
		translate(v=[-40,0,0])
		cube(size=[40,10,80]);
	
		translate(v=[70,-5,2])
		rotate(a=[0,-25,0])
		translate(v=[0,0,0])
		cube(size=[40,10,80]);
	}
	difference() {
		translate(v=[32,-5,0])
		rotate(a=[0,0,25])
		difference() {
			cube(size=[12,50,60]);
			translate(v=[2,-1,2])
			cube(size=[8,52,60]);
			translate(v=[-1,50,2])
			rotate(a=[30,0,0])
			cube(size=[14,54,80]);
		}
		translate(v=[0,-11,-1])
		cube(size=[71,12,62]);
	}
}

