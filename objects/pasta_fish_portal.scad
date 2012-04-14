difference() {
	cylinder(h = 10, r = 37);
	translate(v = [0, 0, -1]) cylinder(h = 12, r = 32);
	for (i = [0:3]) {
		rotate([0,0,i*90])
		translate(v = [30, -1.5, 6-((i%2)*2)])
		cube(size = [10,3,8]);
	}
}