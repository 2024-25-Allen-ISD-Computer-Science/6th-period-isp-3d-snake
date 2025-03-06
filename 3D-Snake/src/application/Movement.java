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
    
        // Checks if two vectors are opposites (to prevent a 180-degree turn).
        private boolean isOpposite(Vector3f v1, Vector3f v2) {
            // This simple check assumes normalized directions.
            return v1.x == -v2.x && v1.y == -v2.y && v1.z == -v2.z;
        }
    
        // Rotates the snake's direction to the left around the global up axis (0,1,0).
        public void turnLeft() {
            direction = rotateVector(direction, new Vector3f(0, 1, 0), ROTATION_ANGLE);
        }
    
        // Rotates the snake's direction to the right around the global up axis (0,1,0).
        public void turnRight() {
            direction = rotateVector(direction, new Vector3f(0, 1, 0), -ROTATION_ANGLE);
        }
    
        // Rotates the snake's direction upward. Here, we rotate around the left vector,
        // which is computed as the cross product of the current direction and the global up (0,1,0).
        public void turnUp() {
            Vector3f left = new Vector3f(direction.z, 0, -direction.x);
            left.normalize();
            direction = rotateVector(direction, left, ROTATION_ANGLE);
        }
    
        // Rotates the snake's direction downward.
        public void turnDown() {
            Vector3f left = new Vector3f(direction.z, 0, -direction.x);
            left.normalize();
            direction = rotateVector(direction, left, -ROTATION_ANGLE);
        }
    
    