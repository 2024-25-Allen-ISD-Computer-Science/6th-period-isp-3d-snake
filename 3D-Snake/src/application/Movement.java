public void changeDirection(Vector3f newDir) {
    if (!newDir.equals(direction.negate(new Vector3f()))) direction.set(newDir);
}

public void move() {
    
    if (segments.isEmpty()) return;
    Vector3f prev = new Vector3f(segments.get(0).getPosition());
    segments.get(0).incPosition(direction.x, direction.y, direction.z);
   
    for (int i = 1; i < segments.size(); i++) {
        Vector3f curr = new Vector3f(segments.get(i).getPosition());
        segments.get(i).setPosition(prev);
        prev.set(curr);
    }
}

