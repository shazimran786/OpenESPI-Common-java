/*
 * Copyright 2013, 2014, 2015 EnergyOS.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.energyos.espi.common.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.energyos.espi.common.support.TestUtils;
import org.hibernate.annotations.LazyCollection;
import org.junit.Test;

public class IntervalBlockPersistenceTests {
	@Test
	public void persistence() {
		TestUtils.assertAnnotationPresent(IntervalBlock.class, Entity.class);
		TestUtils.assertAnnotationPresent(IntervalBlock.class, Table.class);
	}

	@Test
	public void interval() {
		TestUtils.assertAnnotationPresent(IntervalBlock.class, "interval",
				Embedded.class);
	}

	@Test
	public void intervalReadings() {
		TestUtils.assertAnnotationPresent(IntervalBlock.class,
				"intervalReadings", OneToMany.class);
		TestUtils.assertAnnotationPresent(IntervalBlock.class,
				"intervalReadings", LazyCollection.class);
	}

	@Test
	public void meterReading() {
		TestUtils.assertAnnotationPresent(IntervalBlock.class, "meterReading",
				ManyToOne.class);
		TestUtils.assertAnnotationPresent(IntervalBlock.class, "meterReading",
				JoinColumn.class);
	}
}
