extends ItemList

var inventory = {}

func _ready():
	# Called when the node is added to the scene for the first time.
	# Initialization here
	pass
	
func add_to_inventory(item):
	if inventory.has(item):
		inventory[item] += 1
	else:
		inventory[item] = 1

func _process(delta):
	clear()
	for item in inventory:
		add_item(item + " * " + str(inventory[item]))
