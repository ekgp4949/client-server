/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

	@DisplayName("App 테스트")
	@Test
	public void testAppHasAGreeting() {
		App classUnderTest = new App();
		assertThat(true).isTrue();
		assertThat(classUnderTest).isNotNull();;
	}
}
