package rpgeeze.controller;

import static rpgeeze.RunGame.KEY_CONTROLS;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.dp.Command;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Model;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.util.AudioThread;
import rpgeeze.util.Direction;
import rpgeeze.view.GameplayView;
import rpgeeze.view.OptionsMenuView;
import rpgeeze.model.entity.PC;

public class GameplayController extends Controller<GameplayView> {
	private MouseEvent prev = null;

	private double ZOOM_STEP = 0.025;

	private HashMap<String, Command> actions;

	private boolean wasPaused;

	public GameplayController(GameManager manager, GameplayView view) {
		super(manager, view);
		actions = new HashMap<String, Command>();
		actions.put("Pause Game", new Command() {
			public void execute() {
				getManager().getModel().togglePaused();
			}
		});
		actions.put("Inventory View", new Command() {
			public void execute() {
				getView().toggleInventoryVisible();
			}
		});
		actions.put("Options", new Command() {
			public void execute() {
				wasPaused = getManager().getModel().isPaused();
				LogManager.getInstance().log("Model paused state is " + wasPaused, "CONTROLLER");
				OptionsMenuView omv = new OptionsMenuView(getManager());
				OptionsMenuController omc = new OptionsMenuController(getManager(), omv);
				getManager().pushState(omv, omc);
			}
		});

		
		actions.put("Stats View", new Command(){
			public void execute(){
				getView().toggleStatsVisible();
			}
		});

		actions.put("Use Weapon", new Command() {
			public void execute() {
				PC pc = getManager().getModel().getAvatar();
				Direction facing = getManager().getModel().getAvatar().getFacingDirection();
				Tile next = pc.getTile().adjacentTile(facing);
				while (next.isPassable()) {
					if (next.getEntity() != null) {
						//Affect Stats of him
						System.out.println("Hit the target!");
					}
					next = next.adjacentTile(facing);
				}
				try {
					String weap = pc.getEquipment().getWeapon().toString();
					AudioThread at = AudioThread.getInstance(weap, AudioThread.CLIP);
					at.start();
				}
				catch (Exception e) { LogManager.getInstance().log("Error playing sound for the weapon", "", Message.Type.ERROR); }
			}
		});
		actions.put("Use Spell", new Command() {
			public void execute() {
				System.out.print("Use Spell");
			}
		});
>>>>>>> .r1104
		actions.put("Move North", moveEncapsulate(Direction.NORTH));
		actions.put("Move South", moveEncapsulate(Direction.SOUTH));
		actions.put("Move East", moveEncapsulate(Direction.EAST));
		actions.put("Move West", moveEncapsulate(Direction.WEST));
		actions.put("Move Northeast", moveEncapsulate(Direction.NORTHEAST));
		actions.put("Move Northwest", moveEncapsulate(Direction.NORTHWEST));
		actions.put("Move Southeast", moveEncapsulate(Direction.SOUTHEAST));
		actions.put("Move Southwest", moveEncapsulate(Direction.SOUTHWEST));
	}

	protected Command moveEncapsulate(final Direction direction) {
		return new Command() {
			public void execute() {
				Model model = getManager().getModel();
				Entity avatar = model.getAvatar();
				model.queueCommand(new MoveCommand(avatar, direction));
			}
		};
	}
	
	public void reactToChange() {
		switch(getView().getState()) {
		case NORMAL:
			getManager().getModel().start();
			LogManager.getInstance().log("Restoring paused state to " + wasPaused, "CONTROLLER");
			getManager().getModel().setPaused(wasPaused);
			break;
		case HIDDEN:
			getManager().getModel().setPaused(true);
			break;
		}

	}

	public void mouseReleased(MouseEvent e) {
		prev = null;
	}

	public void mousePressed(MouseEvent e) {
		String name = getView().pickClosest(GLU.getCurrentGL(), e.getPoint());
		if(name != null) {
			final String id = name.replaceAll("Inventory Item ", "");
			if(id.matches("\\d+")) {
				final int i = Integer.parseInt(id);
				if(e.getButton() == MouseEvent.BUTTON3) {
					getManager().getModel().queueCommand(new Command() {
						public void execute() {
							getManager().getModel().getAvatar().dropItemAt(i);
						}
					});
				}
				if(e.getButton() == MouseEvent.BUTTON1) {
					getManager().getModel().queueCommand(new Command() {
						public void execute() {
							Entity avatar = getManager().getModel().getAvatar(); 
							avatar.getInventory().getItemAt(i).use(avatar);
						}
					});
				}
			}
			else {
				final String id2 = name.replaceAll("Equipment Item ", "");
				if(id2.matches("\\d+") && e.getButton() == MouseEvent.BUTTON3) {
					final int i = Integer.parseInt(id2);
					getManager().getModel().queueCommand(new Command() {
						public void execute() {
							Entity avatar = getManager().getModel().getAvatar();
							System.out.println(i);
							switch(i) {
							case 1:
								avatar.getEquipment().getHead().unequip(avatar);
								break;
							case 3:
								avatar.getEquipment().getAuxiliary().unequip(avatar);
								break;
							case 4:
								avatar.getEquipment().getArmor().unequip(avatar);
								break;
							case 5:
								avatar.getEquipment().getWeapon().unequip(avatar);
								break;
							case 7:
								avatar.getEquipment().getBoots().unequip(avatar);
								break;
							}
						}
					});
				}
			}
		}
		switch(e.getButton()) {
		case MouseEvent.BUTTON3:
			break;
//		case MouseEvent.BUTTON3:
//			prev = e;
//			break;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(prev != null) {
			double dy = e.getPoint().getY() - prev.getPoint().getY();
			getView().zoom(dy * ZOOM_STEP);
			prev = e;
		}
	}

	public void keyPressed(KeyEvent e){
		LogManager lm = LogManager.getInstance();
		String keyPress = KeyEvent.getKeyText(e.getKeyCode());
		String action = null;
		for(Entry<String, String> entry: KEY_CONTROLS.entrySet())
			if(entry.getValue().equals(keyPress)) {
				action = entry.getKey();
				break;
			}
		lm.log("Pressed " + keyPress + ", " + (action == null ? "no action" : "action is " + action) , "CONTROLLER");
		if(action != null) {
			Command cmd = actions.get(action);
			if(cmd != null)
				cmd.execute();
		}
	}
}
