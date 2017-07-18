package com.lambdaherding.edi.ch07;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

@SuppressWarnings("static-method")
public class GridShapesTest {
	/**
	 * Test various aspects of the {@link GridShapes#transform(float, float, Point)} function by creating a new GridShapes
	 * object containing a single shape consisting of a single point, then looking at what it was translated to.
	 * 
	 * TODO: this seems awkward; surely there's a better way of doing it?
	 */
	@Test
	public void testTransformScaleUp() {
		GridShapes out = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 2, 2 ) ) ) )
				.transform( 3.5f, 4.5f, new Point( 0, 0 ) );

		assertThat( out.getShapes(), is( Collections.singletonList( Collections.singletonList( new Point( 7, 9 ) ) ) ) );
	}

	@Test
	public void testTransformScaleDown() {
		GridShapes out = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 12, 11 ) ) ) )
				.transform( 0.25f, 0.5f, new Point( 0, 0 ) );

		assertThat( out.getShapes(), is( Collections.singletonList( Collections.singletonList( new Point( 3, 5 ) ) ) ) );
	}

	@Test
	public void testTransformTranslate() {
		GridShapes out = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 2, 2 ) ) ) )
				.transform( 1f, 1f, new Point( 5, -3 ) );

		assertThat( out.getShapes(), is( Collections.singletonList( Collections.singletonList( new Point( 7, -1 ) ) ) ) );
	}

	@Test
	public void testTransformTranslateAndScale() {
		GridShapes out = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 2, 2 ) ) ) )
				.transform( 2.5f, 3f, new Point( 5, -3 ) );

		assertThat( out.getShapes(), is( Collections.singletonList( Collections.singletonList( new Point( 10, 3 ) ) ) ) );
	}

	/**
	 * Now also test that the transform function is applied to *all* points of *all* shapes in a GridShapes object
	 */
	@Test
	public void testTransformMultiple() {
		GridShapes out = new GridShapes(
				Arrays.asList(
						Arrays.asList( new Point( 1, 2 ), new Point( 2, 1 ), new Point( 3, 4 ) ),
						Arrays.asList( new Point( -1, 0 ), new Point( -3, -4 ), new Point( 0, 0 ) ) ) )
				.transform( 2.5f, 3f, new Point( 5, -3 ) );

		assertThat( out.getShapes(), is( Arrays.asList(
						Arrays.asList( new Point( 7, 3 ), new Point( 10, 0 ), new Point( 12, 9 ) ),
						Arrays.asList( new Point( 3, -3 ), new Point( -2, -15 ), new Point( 5, -3 ) ) ) ) );
	}

	/**
	 * Test the reflectX function on various input.
	 */
	@Test
	public void testReflectX() {
		GridShapes in = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 5, 7 ) ) ) );
		assertThat( in.reflectX( 4 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 3, 7 ) ) ) ) );
		assertThat( in.reflectX( 7 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 9, 7 ) ) ) ) );
		assertThat( in.reflectX( 2 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( -1, 7 ) ) ) ) );
		assertThat( in.reflectX( -1 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( -7, 7 ) ) ) ) );
		assertThat( in.reflectX( 5 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, 7 ) ) ) ) );
	}

	/**
	 * Test the reflectY function on various input.
	 */
	@Test
	public void testReflectY() {
		GridShapes in = new GridShapes( Collections.singletonList( Collections.singletonList( new Point( 5, 7 ) ) ) );
		assertThat( in.reflectY( 4 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, 1 ) ) ) ) );
		assertThat( in.reflectY( 9 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, 11 ) ) ) ) );
		assertThat( in.reflectY( 2 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, -3 ) ) ) ) );
		assertThat( in.reflectY( -1 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, -9 ) ) ) ) );
		assertThat( in.reflectY( 7 ).getShapes(),
				is( Collections.singletonList( Collections.singletonList( new Point( 5, 7 ) ) ) ) );
	}
}
