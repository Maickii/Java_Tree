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
use `setPositionX(int positionX)` and `setPositionY(int positionY)` to set position of the root branch. The value of X starts at 0 and increases from left to right of your screen, while the Y value starts at 0 and increases from top to bottom of your screen. 
3. **Angle position of trees**  
use `tree.setTreeAngle(angle);` to set the angle of the tree. Angle is measured in counter-clockwise degrees, where 0 is the right-most of the screen and 180 is the left-most of the screen.
3. **Angle separation**  
use `tree.setSeparationAngle(Angle);` to set the angle seperation between branches. This angle will dictate how far appart should each branch be from its sibling branch. You can get funny/interesiting looking trees if you play with this property. Try setting it to 60, 90, or more if you want to have crazy looking trees.
5. **Delay between paintings**    
The Tree class has a method `slowMotion()` which aesthetically draws the tree, by reducing the time between frames.
If you wish to use a constant time between frames you have to manually add `delay(long delay)` in the method `drawTree()` instead of using `slowMotion()`.
Further development is needed easily switch between these settings at optimal performance. 

### Done!
Note: this setting might be overwritten if the safety is on.
After you are done editing your tree, just call `tree.draw(boolean safety);` to draw it!


![alt text](https://github.com/Maickii/Java_Tree/blob/master/2017-03-05%20(2).png "Tree")
