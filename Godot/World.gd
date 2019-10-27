extends Spatial

# class member variables go here, for example:
# var a = 2
# var b = "textvar"
var theta = 1
var phi = 0
var r = 10
var maxTheta = 2
var minTheta = 0.2

func _ready():
	# Called when the node is added to the scene for the first time.
	# Initialization here
	pass

func _process(delta):
	# Called every frame. Delta is time since last frame.
	# Update game logic here.
	var character = get_node("Spatial")
	var cam = get_node("Camera")
	#character.translate(Vector3(0.01,0,0))
	if Input.is_key_pressed(KEY_RIGHT):
		phi -= delta
		
	if Input.is_key_pressed(KEY_LEFT):
		phi += delta
	if Input.is_key_pressed(KEY_DOWN):
		theta += delta
		if theta > maxTheta:
			theta = maxTheta
	if Input.is_key_pressed(KEY_UP):
		theta -= delta
		if theta < minTheta:
			theta = minTheta
			
	if Input.is_key_pressed(KEY_D):
		character.translate(-delta * character.transform.basis.x)
	if Input.is_key_pressed(KEY_A):
		character.translate(delta * character.transform.basis.x)
	if Input.is_key_pressed(KEY_S):
		character.translate(-delta * character.transform.basis.z)
	if Input.is_key_pressed(KEY_W):
		character.translate(delta * character.transform.basis.z)
	
	cam.translation = character.translation + r*Vector3(sin(theta)*cos(phi),cos(theta),sin(theta)*sin(phi))
	cam.look_at(character.translation, Vector3(0,1,0))
