public void move() {
    if (segments.isEmpty()) return;

    // Shift every segment to the position of the segment before it
    for (int i = segments.size() - 1; i > 0; i--) {
        segments.get(i).set(segments.get(i - 1));
    }

    // Update the head position by adding the direction vector
    Vector3f head = segments.get(0);
    head.x += direction.x;
    head.y += direction.y;
    head.z += direction.z;
}
