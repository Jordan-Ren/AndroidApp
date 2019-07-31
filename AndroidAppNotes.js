/**Ideas:
1) Every ... 20 pixels along the created path add a square cobblestone image 20 pixels in length --- This will create the path that the mobs will walk on.
2) Mobs follow path, update their position by 10 places along the path per timestep.
3) Get the mob movement working before placing the towers.
4) Tower ideas: Image as button, if you click the button, a opaque circle that shows tower radius is shown, tap again to place the tower.
5) Add collision detection with basic rectangles.
6) Add throwing mechanics using object based programming.**/
//First store all mobs within a list or something, then go through them and update their "i" by 10 places to move them along the path.
public static moveMobs() {
  String[] mobsToDel = [];
  for each (var mob in mobL) {
    try {
      mob.position += 10;
    }
    catch {
      mobsToDel.push(mob);
    }
  }
  for each (var mob in mobsToDel) {
    mobL.del(mob);
  }
}

public class Mobs {
  int loc = 0;

}


def find_collisions(self, loc, height, width):
        for rock in self.formations.rocks:
            if abs(rock['loc'][0] - loc[0]) * 2 < (Constants.ROCK_WIDTH + width):
                if abs(rock['loc'][1] - loc[1]) * 2< (Constants.ROCK_HEIGHT + height):
                    return False
        for new_keepers in self.formations.keepers:
            if abs(new_keepers['loc'][0] - loc[0]) * 2< (Constants.KEEPER_WIDTH + width):
                if abs(new_keepers['loc'][1] - loc[1]) * 2< (Constants.KEEPER_HEIGHT + height):
                    return False
        for demon in self.formations.demons:
            if abs(demon['loc'][0] - loc[0]) * 2< (Constants.DEMON_WIDTH + width):
                if abs(demon['loc'][1] - loc[1]) * 2< (Constants.DEMON_HEIGHT + height):
                    return False
        for VHS in self.formations.VHS:
            if abs(VHS['loc'][0] - loc[0]) * 2< (Constants.VHS_WIDTH + width):
                if abs(VHS['loc'][1] - loc[1]) * 2< (Constants.VHS_HEIGHT + height):
                    return False
        #I split the path up into rectangles with each rectangle being a segment
        #of the path. check each rectangle
        rectangle_corners = []
        for i in range(len(self.path_corners)-1):
            temp = []
            if self.path_corners[i][0] - self.path_corners[i+1][0] == 0:
                if self.path_corners[i][1] - self.path_corners[i+1][1] < 0:
                    x_start = self.path_corners[i][0] - (Constants.PATH_THICKNESS-1)/2
                    x_end = self.path_corners[i +1][0] + (Constants.PATH_THICKNESS-1)/2
                    y_start = self.path_corners[i][1] - (Constants.PATH_THICKNESS-1)/2
                    y_end = self.path_corners[i + 1][1] + (Constants.PATH_THICKNESS-1)/2
                else:
                    x_start = self.path_corners[i][0] - (Constants.PATH_THICKNESS-1)/2
                    x_end = self.path_corners[i +1][0] + (Constants.PATH_THICKNESS-1)/2
                    y_start = self.path_corners[i][1] + (Constants.PATH_THICKNESS-1)/2
                    y_end = self.path_corners[i + 1][1] - (Constants.PATH_THICKNESS-1)/2
            else:
                if self.path_corners[i][0] - self.path_corners[i+1][0] < 0:
                    x_start = self.path_corners[i][0] - (Constants.PATH_THICKNESS-1)/2
                    x_end = self.path_corners[i +1][0] + (Constants.PATH_THICKNESS-1)/2
                    y_start = self.path_corners[i][1] - (Constants.PATH_THICKNESS-1)/2
                    y_end = self.path_corners[i + 1][1] + (Constants.PATH_THICKNESS-1)/2
                else:
                    x_start = self.path_corners[i][0] + (Constants.PATH_THICKNESS-1)/2
                    x_end = self.path_corners[i +1][0] - (Constants.PATH_THICKNESS-1)/2
                    y_start = self.path_corners[i][1] - (Constants.PATH_THICKNESS-1)/2
                    y_end = self.path_corners[i + 1][1] + (Constants.PATH_THICKNESS-1)/2
            temp.append(x_start)
            temp.append(x_end)
            temp.append(y_end)
            temp.append(y_start)
            rectangle_corners.append(temp)
        for rectangle in rectangle_corners:
            if abs((rectangle[0] + rectangle[1])/2 - loc[0]) * 2 < (abs(rectangle[0] - rectangle[1]) + width):
                if abs((rectangle[2] + rectangle[3])/2 - loc[1]) * 2 < (abs(rectangle[2] - rectangle[3]) + height):
                    return False
        return True
