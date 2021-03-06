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

package org.energyos.espi.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.energyos.espi.common.models.atom.EntryType;
import org.energyos.espi.common.models.atom.LinkType;

class EntryLookupTable {
	private Map<String, EntryType> entryMap;

	public EntryLookupTable(List<EntryType> entries) {
		entryMap = new HashMap<>();

		addSelfLinks(entries);
		addRelatedLinks(entries);
	}

	public EntryType getUpEntry(EntryType entry) {
		return entryMap.get(getUpLinkHref(entry));
	}

	public List<EntryType> getRelatedEntries(EntryType entryType) {
		List<EntryType> relatedEntries = new ArrayList<>();
		for (LinkType link : entryType.getLinks()) {
			if (link.getRel().equals("related")) {
				relatedEntries.add(entryMap.get(link.getHref()));
			}
		}
		return relatedEntries;
	}

	private void addSelfLinks(List<EntryType> entries) {
		for (EntryType entry : entries) {
			entryMap.put(getSelfLinkHref(entry), entry);
		}
	}

	private void addRelatedLinks(List<EntryType> entries) {
		for (EntryType entry : entries) {
			for (LinkType link : entry.getLinks()) {
				if (link.getRel().equals("related")
						&& !entryMap.containsKey(link.getHref())) {
					entryMap.put(link.getHref(), entry);
				}
			}
		}
	}

	private String getSelfLinkHref(EntryType entry) {
		return getLinkHrefForType(entry, "self");
	}

	private String getUpLinkHref(EntryType entry) {
		return getLinkHrefForType(entry, "up");
	}

	private String getLinkHrefForType(EntryType entry, String type) {
		for (LinkType link : entry.getLinks()) {
			if (link.getRel().equals(type)) {
				return link.getHref();
			}
		}
		return null;
	}
}
