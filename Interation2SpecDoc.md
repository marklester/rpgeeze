-Mount/Vehicle
> -Subclass of Entity
> -


-AI / NPC Stuff

> -New class called AIBrain
> -Contains all references to all AIs' techniques?
> -Model contains a movement priority queue that consists of move commands for both the avatar and the NPC. Higher priority goes to those Entities closest to the Avatar's location (so, avatar will always have highest priority).
> Types:
    * ells stuff
    * illager - don't do anything cool, but waste your time
    * onsters - Have specific stats & occupation



Skills

> -Abstract class -> Skill
> -Each Occupation will have specific List of Skills (literally, a List

&lt;Skill&gt;

)
> -Each Entity will have a container of Skills
> -When an Entity is created, the Occupation will copy/clone over the "unique" skills associated with that occupation.
> -As the Entity plays the game and level's up, new Skills can be aquired and added to the Skill container
> -The level/counter of skill-points will be kept in the individual Skill
> -Exceptions can be used to ensure a occupationX-specific skill is not added to a different occupation type.
> -We will have a SkillView, similar in implementation to the Inventory.
> > -This will list all the Skills currently in the avatar's container of Skills. When new skill-points are acquired during gameplay, a message is sent to the console to tell the player to open the SkillView and "distribute" the points to skills of their choice.


> -Some skills will be "usable" - e.g. a Sneak can pick-pocket, but other Skills (such as "one handed item") are not actually usable, so they WONT appear when the ActionPrompt appears (see below)


Actions

> -When the avatar approaches a NPC, the ActionPrompt will appear with choices (talk, attack, use skill, use item...)
> -A monster will bypass this menu and start to attack automatically
> -


Etc.

> -Smoother movement. Especially during teleporting and respawning. Possible idea is to have different notifications for the observer (View).

> -Must get a Gator1 to get into a door. Once in this door, we open into Team BowBlossom's crap-map.

> -Take out isPassable() from all terrains, and have Obstacle throw a CantMoveHereBitchExcetion. This will be caught by the function moving the Entity


Threads

  1. GUI (implict) OpenGL (pre-OpenGL -> was updates of the View)
> 2. Updates of the Model
> 3. Listen for controller (implicit, keylisteners and mouse listeners)
> 4. AI (Needs to be written)
> 5. Audio Thread (crappily written)