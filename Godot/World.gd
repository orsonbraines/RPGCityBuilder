extends Spatial

# class member variables go here, for example:
# var a = 2
# var b = "textvar"
var theta = 1
var phi = 0
var r = 10
var maxTheta = 2
var minTheta = 0.2
var houseScene = preload("res://house.tscn")
var build = false

func _ready():
	# Called when the node is added to the scene for the first time.
	# Initialization here
	pass

func _process(delta):
	# Called every frame. Delta is time since last frame.
	# Update game logic here.
	var character = get_node("KinematicBody")
	var cam = get_node("Camera")
	if Input.is_key_pressed(KEY_RIGHT):
		phi += delta
		character.setState("move")
	if Input.is_key_pressed(KEY_LEFT):
		phi -= delta
		character.setState("move")
	if Input.is_key_pressed(KEY_DOWN):
		theta += delta
		if theta > maxTheta:
			theta = maxTheta
		character.setState("move")
	if Input.is_key_pressed(KEY_UP):
		theta -= delta
		if theta < minTheta:
			theta = minTheta
		character.setState("move")
	if Input.is_key_pressed(KEY_N):
		if build == false:
			var node = houseScene.instance()
			node.translation = character.translation + character.transform.basis.x * 20
			add_child(node)
	else:
		build = false	
	
	character.rotation = Vector3(0,-phi + PI,0)
	cam.translation = character.translation + Vector3(0,5,0) + r*Vector3(sin(theta)*cos(phi),cos(theta),sin(theta)*sin(phi))
	cam.look_at(character.translation  + Vector3(0,5,0) , Vector3(0,1,0))