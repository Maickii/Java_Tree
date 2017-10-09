# Java_Tree

The purpose of this project is to create visually apealing trees in Java using Trees data structures. 

<a href="https://imgflip.com/gif/1wtb3u"><img src="https://i.imgflip.com/1wtb3u.gif" title="made at imgflip.com"/></a>
<a href="https://imgflip.com/gif/1wtbd5"><img src="https://i.imgflip.com/1wtbd5.gif" title="made at imgflip.com"/></a>
<a href="https://imgflip.com/gif/1wtbk7"><img src="https://i.imgflip.com/1wtbk7.gif" title="made at imgflip.com"/></a>
<a href="https://imgflip.com/gif/1wtbnx"><img src="https://i.imgflip.com/1wtbnx.gif" title="made at imgflip.com"/></a>

### Change it to your liking!
It is easy modify many properties of the trees.
Here is a list of the things you can change in this code.
1. **Number of child branches.**  
Just add more Tree objects `Tree tree = new Tree(15);` to main.
```Java
   public static void main(String[] args) {
      Tree tree = new Tree(limit);
   }
```

2. **Size of trees**  
use `tree.setSize(size);` to change the size of the tree.
3. **Positions**  
@param positionX sets the X position of the root branch. This is based on
	 * the pixels on your screen, 0 being the left side of your screen, and
	 * increasing in value as you move to the right of your screen. 
	 * <br>Note: this setting might be overwritten if the safety is on.
	 */
	public void setPositionX(int positionX)
3. **Angle position of trees**  
use `tree.setTreeAngle(angle);` to set the angle of the tree. Angle is measured in counter-clockwise degrees, where 0 is the right-most of the screen  and 180 is the left-most of the screen.
3. **Angle separation**  
use `tree.setSeparationAngle(Angle);` to set the angle seperation between branches. This angle will dictate how far appart should each branch be from its sibling branch. You can get funny/interesiting looking trees if you play with this property. 

5. **Delay between paintings**    

### Done!
After you are done editing your tree, just call `tree.draw();` to draw it!


![alt text](https://github.com/Maickii/Java_Tree/blob/master/2017-03-05%20(2).png "Tree")
