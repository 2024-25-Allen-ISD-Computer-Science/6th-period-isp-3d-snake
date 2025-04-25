package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import application.controllers.CameraController;
import application.entities.EntityAbstract;

public class Transformation {
    public static Matrix4f createTransformationMatrix(EntityAbstract entity) {
        /* Initialize Variables */
        Matrix4f matrix = new Matrix4f();

        /* Transformation */
        matrix.identity(); // create new matrix
        matrix.translate(entity.getPos()); // moves the matrix
        matrix.rotateX((float) Math.toRadians(entity.getRotation().x)); // rotates (x-axis)
        matrix.rotateY((float) Math.toRadians(entity.getRotation().y)); // rotates (y-axis)
        matrix.rotateZ((float) Math.toRadians(entity.getRotation().z)); // rotates yaw (z-axis)
        matrix.scale(entity.getScale()); // scales the model

        /* Return */
        return matrix;
    }

    public static Matrix4f getViewMatrix(CameraController camera) {
        Vector3f pos = camera.getPosition();
        Vector3f rot = camera.getRotation();
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.rotate((float) Math.toRadians(rot.x), new Vector3f(1, 0, 0))
                .rotate((float) Math.toRadians(rot.y), new Vector3f(0, 1, 0))
                .rotate((float) Math.toRadians(rot.z), new Vector3f(0, 0, 1));
        matrix.translate(-pos.x, -pos.y, -pos.z);
        return matrix;
    }
}