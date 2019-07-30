var corners = [[0,0],[0,100],[50,100],[50,50],[25,50]];
var path = [];
for (var i = 0; i< (corners.length -1); i++) {
  //This is checing if x stays the same
  if (corners[i][0] == corners[i+1][0]) {
    //If the path moves down
    if (corners[i][1] - corners[i+1][1] > 0) {
      for (var j = 0; j < (corners[i][1] - corners[i+1][1]); j++) {
        path.push([corners[i][0], corners[i][1] - j]);
      }
    }
    else {
      for (var j = 0; j < (corners[i+1][1] - corners[i][1]); j++) {
        path.push([corners[i][0], corners[i][1] + j]);
      }
    }
  }
  else {
    if (corners[i][0] - corners[i+1][0] > 0) {
      for (var k = 0; k < (corners[i][0] - corners[i+1][0]); k++) {
        path.push([corners[i][0] - k, corners[i][1]]);
      }
    }
    else {
      for (var k = 0; k < (corners[i+1][0] - corners[i][0]); k++) {
        path.push([corners[i][0] + k, corners[i][1]]);
      }
    }
  }
}
path.push(corners[-1]);
console.dir(path, {'maxArrayLength': null});
