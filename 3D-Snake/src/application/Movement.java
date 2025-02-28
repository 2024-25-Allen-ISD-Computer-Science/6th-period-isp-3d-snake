class Snake {
    // A list of 3D vectors representing the snake segments
    private List<Vector3f> segments = new ArrayList<>();
    // The current movement direction (initially moving to the right)
    public Vector3f direction = new Vector3f(1, 0, 0);
    
    // Boundary for wrapping (adjust as needed for your game world)
    private final float BOUNDARY = 10.0f;

    public Snake() {
        // Initialize the snake with 3 segments (head, body, tail)
        segments.add(new Vector3f(0, 0, 0));      // Head
        segments.add(new Vector3f(-1, 0, 0));       // Body
        segments.add(new Vector3f(-2, 0, 0));       // Tail
    }

    // Moves the snake by shifting all segments and updating the head.
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
        
        // Wrap the head around boundaries if it goes out of bounds.
        if (head.x > BOUNDARY) head.x = -BOUNDARY;
        else if (head.x < -BOUNDARY) head.x = BOUNDARY;
        if (head.y > BOUNDARY) head.y = -BOUNDARY;
        else if (head.y < -BOUNDARY) head.y = BOUNDARY;
        if (head.z > BOUNDARY) head.z = -BOUNDARY;
        else if (head.z < -BOUNDARY) head.z = BOUNDARY;
    }
