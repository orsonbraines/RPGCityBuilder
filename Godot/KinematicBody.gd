extends KinematicBody

# class member variables go here, for example:
# var a = 2
# var b = "textvar"

func _ready():
	# Called when the node is added to the scene for the first time.
	# Initialization here
	pass
	
func _physics_process(delta):
	var motion = Vector3()
	var scale_coef = delta * 3
	if Input.is_key_pressed(KEY_D):
		motion += scale_coef * transform.basis.z
	if Input.is_key_pressed(KEY_A):
		motion -= scale_coef * transform.basis.z
	if Input.is_key_pressed(KEY_S):
		motion -= scale_coef * transform.basis.x
	if Input.is_key_pressed(KEY_W):
		motion += scale_coef * transform.basis.x
	move_and_collide(motion)

#func _process(delta):
#	# Called every frame. Delta is time since last frame.
#	# Update game logic here.
#	pass
