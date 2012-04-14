difference() {
	cylinder(h = 7.5, r = 37);
	translate(v = [0, 0, -1]) cylinder(h = 9.5, r = 32);
	for (i = [0:3]) {
		rotate([0,0,i*90])
		translate(v = [30, -1.5, 4])
		cube(size = [10,3,4]);
	}
}