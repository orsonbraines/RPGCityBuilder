extends KinematicBody

# class member variables go here, for example:
# var a = 2
# var b = "textvar"
var ray_length = 5
var state = "none"
var state_count = 0
var harvesting = "none"

func setState(next_state):
	if next_state != state:
		print("changing charater state to ", next_state)
		state_count = 0
		state = next_state

func _ready():
	# Called when the node is added to the scene for the first time.
	# Initialization here
	pass
	
func _physics_process(delta):
	state_count += delta
	var motion = Vector3()
	var scale_coef = delta * 8
	if Input.is_key_pressed(KEY_D):
		motion += scale_coef * transform.basis.z
		setState("move")
	if Input.is_key_pressed(KEY_A):
		motion -= scale_coef * transform.basis.z
		setState("move")
	if Input.is_key_pressed(KEY_S):
		motion -= scale_coef * transform.basis.x
		setState("move")
	if Input.is_key_pressed(KEY_W):
		motion += scale_coef * transform.basis.x
		setState("move")
	if Input.is_key_pressed(KEY_SPACE):
		var space_state = get_world().direct_space_state
		var from = translation
		var to = translation + ray_length * transform.basis.x
		var result = space_state.intersect_ray(from, to, [self])
		if result and result.collider.get("name") == "GridMap":
			var s = result.collider.cell_size
			var r = result.position
			harvesting = false
			match result.collider.get_cell_item(floor(r.x / s.x), floor(r.y / s.y), floor(r.z / s.z)):
				0:
					harvesting = "Tree"
				1:
					harvesting = "MiningRock"
			if harvesting:
				setState("harvest")
	move_and_collide(motion)
	
	
	if state == "harvest" and state_count >= 2:
		var inventory = get_parent().get_node("UI/Inventory")
		print("harvesting ", harvesting);
		if harvesting == "Tree":
			inventory.add_to_inventory("Log")
			print("harvested log") 
		elif harvesting == "MiningRock":
			inventory.add_to_inventory("Ore")
			print("harvested ore") 
		state_count = 0;
#func _process(delta):
#	# Called every frame. Delta is time since last frame.
#	# Update game logic here.
#	pass
