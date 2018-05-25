package game.modele.utils.ActionConsumer;
import java.util.function.Consumer;
import game.modele.entity.Entity;
import game.modele.utils.graph.Graph;
import game.modele.world.World;
/* Cette Class r�f�rence toutes les fonctions applicables sur les entit�es
 *
 */
public enum FunctionBank {	

	MoveIceDownAND(e -> e.addY(e.speed * 2/30 * e.slow)),
	MoveIceDown(e -> e.addY(e.speed * e.slow / 15)),
	MoveIceUpAND(e -> e.addY(-e.speed * 2/30 * e.slow)),
	MoveIceUp(e -> e.addY(-e.speed * e.slow / 15)),
	MoveIceRightAND(e -> e.addX(e.speed * 2/30 * e.slow)),
	MoveIceRight(e -> e.addX(e.speed * e.slow / 15)),
	MoveIceLeftAND(e -> e.addX(-e.speed * 2/30 * e.slow)),
	MoveIceLeft(e -> e.addX(-e.speed * e.slow / 15)),


	SimpleMove(e-> {
		if(!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active) {
			e.speed = e.baseSpeed;
			
		}else {
			//World.currentMap.g.Dijkstra((int)e.coordonnes.getX(),(int)e.coordonnes.getY());
			
		}
		if(e.moveDown.active) {
			if(e.moveLeft.active ^ e.moveRight.active) {
				e.addY(e.speed * 2/3 * e.slow);
			}
			else {
				if(e.speed < e.maxSpeed) {
					e.speed += e.acce;
				}
				e.addY(e.speed * e.slow);
			}
		}
		if(e.moveUP.active) {
			if(e.moveLeft.active ^ e.moveRight.active)
			{	
				e.addY(-e.speed * 2/3 * e.slow);	
			}else
			{
				if(e.speed < e.maxSpeed) {
					e.speed += e.acce;
				}
				e.addY(-e.speed * e.slow);	
			}
		}
		if(e.moveLeft.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addX(-e.speed * 2/3 * e.slow);
			}		else
			{	
				if(e.speed < e.maxSpeed) {
					e.speed += e.acce;
				}
				e.addX(-e.speed * e.slow);
			}
		}
		if(e.moveRight.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addX(e.speed * 2/3 * e.slow);
			}else
			{
				if(e.speed < e.maxSpeed) {
					e.speed += e.acce;
				}
				e.addX(e.speed * e.slow); 
			}
		}
	}),
	SimpleMovement(e -> {
		if(e.moveDown.active || e.moveUP.active || e.moveLeft.active || e.moveRight.active) {
			e.incAnim();
		}
		if(!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active) {
			e.resetAnim();
		}}),

	IceMove(e ->{
		if(!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active) {
			e.speed = e.baseSpeed;
		}
		if(e.moveDown.active) {
			if(e.moveLeft.active ^ e.moveRight.active) {
				e.addAction(new CountActionConsumer(60, FunctionBank.MoveIceDownAND));
			}
			else {
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceDown));
			}
		}
		if(e.moveUP.active) {
			if(e.moveLeft.active ^ e.moveRight.active)
			{	
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceUpAND));
			}else
			{
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceUp));
			}
		}
		if(e.moveLeft.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceLeftAND));
			}		else
			{	
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceLeft));
			}
		}
		if(e.moveRight.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceRightAND));
			}else
			{
				e.addAction(new CountActionConsumer(60,FunctionBank.MoveIceRight));
			}
		}}),
	IAMove(e -> {
		int x = (int)e.coordonnes.getY();
		int y = (int)e.coordonnes.getX();
		try {
		if(World.currentMap.g.direction[x][y].getValue() == Graph.right) {
			e.moveLeft.active = true;
			e.moveRight.active = false;
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.left) {
			e.moveLeft.active = false;
			e.moveRight.active = true;
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.top) {
			e.moveUP.active = true;
			e.moveDown.active = false;
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.bot) {
			e.moveDown.active = true;
			e.moveUP.active = false;
		}
		
		
		
		}catch(NullPointerException n) {}
	});

	public Consumer<Entity> element;
	private FunctionBank(Consumer<Entity> c) {
		this.element = c;
	}
	public int index() {
		return this.ordinal();
	}
}
