package com.manulaiko.tabitha.utils;

/**
 * Point class.
 * ============
 *
 * Represents a point in a 2D map.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Point
{
    ///////////////////////////////////
    // Static methods and properties //
    ///////////////////////////////////

    /**
     * Adds 2 points.
     *
     * @param one Point 1.
     * @param two Point 2.
     *
     * @return Point 1 + Point 2.
     */
    public static Point plus(Point one, Point two)
    {
        return Point.plus(one.x(), one.y(), two.x(), two.y());
    }

    /**
     * Adds 2 points.
     *
     * @param one Point 1.
     * @param two Point 2 x & y.
     *
     * @return Point 1 - Point(two, two).
     */
    public static Point plus(Point one, double two)
    {
        return Point.plus(one.x(), one.y(), two, two);
    }

    /**
     * Adds 2 points.
     *
     * @param one  Point 1.
     * @param twoX Point 2 x.
     * @param twoY Point 2 y.
     *
     * @return Point 1 - Point(twoX, twoY).
     */
    public static Point plus(Point one, double twoX, double twoY)
    {
        return Point.plus(one.x(), one.y(), twoX, twoY);
    }

    /**
     * Adds 2 points.
     *
     * @param oneX Point 1 x.
     * @param oneY Point 1 y.
     * @param twoX Point 2 x.
     * @param twoY Point 2 y.
     *
     * @return Point(oneX, oneY) + Point(twoX, twoY).
     */
    public static Point plus(double oneX, double oneY, double twoX, double twoY)
    {
        return new Point(
                oneX + twoX,
                oneY + twoY
        );
    }

    /**
     * Adds 2 points.
     *
     * @param one Point 1 x & y.
     * @param two Point 2 x & y.
     *
     * @return Point(one, one) + Point(two, two).
     */
    public static Point plus(double one, double two)
    {
        return new Point(one + two);
    }
    /**
     * Minus 2 points.
     *
     * @param one Point 1.
     * @param two Point 2.
     *
     * @return Point 1 - Point 2.
     */
    public static Point minus(Point one, Point two)
    {
        return Point.minus(one.x(), one.y(), two.x(), two.y());
    }

    /**
     * Minus 2 points.
     *
     * @param one Point 1.
     * @param two Point 2 x & y.
     *
     * @return Point 1 - Point(two, two).
     */
    public static Point minus(Point one, double two)
    {
        return Point.minus(one.x(), one.y(), two, two);
    }

    /**
     * Minus 2 points.
     *
     * @param one  Point 1.
     * @param twoX Point 2 x.
     * @param twoY Point 2 y.
     *
     * @return Point 1 - Point(twoX, twoY).
     */
    public static Point minus(Point one, double twoX, double twoY)
    {
        return Point.minus(one.x(), one.y(), twoX, twoY);
    }

    /**
     * Minus 2 points.
     *
     * @param oneX Point 1 x.
     * @param oneY Point 1 y.
     * @param twoX Point 2 x.
     * @param twoY Point 2 y.
     *
     * @return Point(oneX, oneY) - Point(twoX, twoY).
     */
    public static Point minus(double oneX, double oneY, double twoX, double twoY)
    {
        return new Point(
                oneX - twoX,
                oneY - twoY
        );
    }

    /**
     * Minus 2 points.
     *
     * @param one Point 1 x & y.
     * @param two Point 2 x & y.
     *
     * @return Point(one, one) - Point(two, two).
     */
    public static Point minus(double one, double two)
    {
        return new Point(one - two);
    }

    ///////////////////////////////////////
    // Non static methods and properties //
    ///////////////////////////////////////

    /**
     * X Position.
     */
    private double _x;

    /**
     * Y Position.
     */
    private double _y;

    /**
     * Constructor.
     */
    public Point()
    {
        this(0);
    }

    /**
     * Constructor.
     *
     * @param position X and Y position.
     */
    public Point(double position)
    {
        this(position, position);
    }

    /**
     * Constructor.
     *
     * @param x X position.
     * @param y Y Position.
     */
    public Point(double x, double y)
    {
        this._x = x;
        this._y = y;
    }

    /**
     * Returns X position.
     *
     * @return X position.
     */
    public double x()
    {
        return this._x;
    }

    /**
     * Sets X position.
     *
     * @param x New position.
     */
    public void x(double x)
    {
        this._x = x;
    }

    /**
     * Returns Y position.
     *
     * @return Y Position.
     */
    public double y()
    {
        return this._y;
    }

    /**
     * Sets Y position.
     *
     * @param y New position.
     */
    public void y(double y)
    {
        this._y = y;
    }

    /**
     * Checks the distance between this point and another.
     *
     * @param point Point to calculate the distance.
     *
     * @return The distance between `this` and `point`.
     */
    public double distanceTo(Point point)
    {
        Point distance = Point.minus(this, point);

        return java.lang.Math.hypot(distance.x(), distance.y());
    }

    /**
     * Checks if this point is in a range between two points.
     *
     * @param from Starting position.
     * @param to   The radius of the range.
     *
     * @return `true` if this point is between `from` and `to`, `false` if not.
     */
    public boolean isInRange(Point from, Point to)
    {
        double position = this.distanceTo(from);
        double distance = from.distanceTo(to);

        return (position <= distance);
    }

    /**
     * Parses the object to a string.
     *
     * @return The vector as a String.
     */
    @Override
    public String toString()
    {
        return this.x() + "," + this.y();
    }

    /**
     * Checks if this vector is the same as `obj`.
     *
     * @param obj Point to check.
     *
     * @return `true` if `obj` is the same point as `this`, `false` if not.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Point)) {
            return false;
        }
        Point p = (Point)obj;

        return (
                p.x() == this.x() &&
                p.y() == this.y()
        );
    }

    /**
     * Hashes the point.
     *
     * @return Hashed point.
     */
    @Override
    public int hashCode()
    {
        return (this.x() + " " + this.y()).hashCode();
    }

    /**
     * Clones and returns the point.
     *
     * @return Cloned point.
     */
    @Override
    public Point clone()
    {
        return new Point(this.x(), this.y());
    }
}
