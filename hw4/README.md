# hw4-Zveref
hw3-Zveref created by GitHub Classroom
Name: Zewen Xu

app url: zx35-hw4-collision.herokuapp.com

slip day:2



                                                         Design Decisions


I disabled some combinations between rotatingStrategy and collisionBehaviuor because they make the animation messy, while they could actually work. If you see animations wired, you could email me to change heroku or enable them in UpdateStateCmd.class 52 and 31;

Model:  
1. Use SINGLETON design pattern to create different strategies, with private structure and public initiate method, thus could avoid inappropriate function initializion.

2. Set NullStrategy and NullObject to strengthen project's robustness, thus could handle bad requests.

3. Use FACTORY design pattern and TEMPLATE design pattern to create strategies, all classes are built on designed Interface. These ensure the extensibility.

4. All attributes of objects are sealed pprivate/protected, only exposing desined method for access. This could avoid mistakes and sterngthen the poject's robustness.

5. Use OBSERVER(PropertyListener) pattern with COMMAND pattern to update and switch object moving, which lowers the future upgrading workload and makes it extensible. Balls and Fishes individually take 2 Arraylists, convenient for doing operations on each of them.

6. one dispathadapter is adopted to communicate with models and controller. In controller I optimize the commands so every POST/GET request and parameters within could be clear on the monitor, which is efficiency for coding and future updating.

7. Set a variant "character" to determine 10 different colliding actions between balls. For "hungary" and "fusion" character, it only works when both of balls are the same character, or executed as default (initialized as normal collide).

8. Use variant "step" to achieve different types of direction-changing linear and also curve movement.

9. Use POINT "direction" to add more extensibility. In MODEL, "direction" determines the rotating direction of objects. In VIEW, fish could use "direction" to instantly adjust canvas rotating degree to simulate the real swimming direction, which enables the fish swim toward 0 ~ 2π!


View:
1. Redesigned the layout of HTML, more clean, more USER-FRIENDLY. Users could use selectors, picking the strategy-type matches to create and remove certain subset of objects and a checkbox to determine if it could be strategy-switched.

2. Strategy-switching is pre-designed, just have to click switch button and it'll be done as designed. A clear button could fast wipe all objects.

3. Fishes are drawn with full javascript methods: rotate and translate the canvas, then save and retore it. Thus they are not affected by HTML mistakes and more robust.

4. Processed the original fish image to remove the white background, making canvas cleaner.

Other:
Use tool package "util" to group util methods, such as randomizers and calculations. This makes codes easy to read and extensible for further changes.



  

