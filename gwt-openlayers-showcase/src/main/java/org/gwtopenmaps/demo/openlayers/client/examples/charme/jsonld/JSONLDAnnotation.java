package org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.Annotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SubsetSelector;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class JSONLDAnnotation extends JSONLDElementArray {

	public JSONLDAnnotation(Annotation annotation) {
		super(new JSONArray());

		this.add(createBodyJson(annotation));
		this.add(createTemporalExtent(annotation));
		this.add(createSubSelector(annotation));
		this.add(createAnno(annotation));
		this.add(createDataSubset(annotation));
		this.add(createAgent(annotation));
		this.add(createTargetDataset(annotation));
		this.add(createAuthor(annotation));
	}

	/**
	 * Creates the "chnode:bodyID" element of the annotation. For example:
	 * 
	 * { 
	 * 	"@id": "chnode:bodyID", 
	 * 	"@type": [
	 * 		"http://www.charme.org.uk/def/user_comment" 
	 *   ],
	 *   "http://www.charme.org.uk/def/hasContent": [ 
	 *     { 
	 *       "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text", 
	 *       "@value": "There is a sampling station here" 
	 *     } 
	 *   ]
	 * },
	 * 
	 * 
	 * @return The JSON-LD Element that represents a chnode:bodyID node.
	 */
	private JSONLDElement createBodyJson(Annotation annotation) {
		JSONLDElement body = new JSONLDElement(new JSONObject());
		body.setId("chnode:bodyID");
		body.setTypeAsArray("http://www.charme.org.uk/def/user_comment");
		body.add(
				"http://www.charme.org.uk/def/hasContent",
				createPredicate(null,
						"http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
						annotation.getContent()));

		return body;
	}

	public String getBodyStr() {
		JSONLDElement bodyElement = new JSONLDElement(findElementWithId("chnode:bodyID"));
		JSONLDElementArray jsonldElementArray = bodyElement
				.get("http://www.charme.org.uk/def/hasContent");
		JSONLDElement firstElem = jsonldElementArray.get(0);
		return firstElem.getValue();
	}

	/**
	 * Creates the "chnode:temporalExtentID" element of the annotation. For
	 * example:
	 * 
	 * { 
	 * 	 "@id": "chnode:temporalExtentID",
	 *   "http://www.charme.org.uk/def/hasCalendar": [ 
	 *     { 
	 *       "@value": "Gregorian" 
	 *     }
	 *   ], 
	 *   "http://www.charme.org.uk/def/hasStart": [ 
	 *   	{ 
	 *        "@type": "http://www.w3.org/2001/XMLSchema#dateTime", 
	 *        "@value": "2008-01-01T00:00:00+00:00" 
	 *      } 
	 *    ], 
	 *    "http://www.charme.org.uk/def/hasStop": [ 
	 *      { 
	 *        "@type": "http://www.w3.org/2001/XMLSchema#dateTime", 
	 *        "@value": "2009-01-01T00:00:00+00:00" 
	 *      } 
	 *    ] 
	 *  },
	 * 
	 * 
	 * @return The JSON-LD Element that represents a chnode:temporalExtentID
	 *         node.
	 */
	private JSONLDElement createTemporalExtent(Annotation annotation) {
		JSONLDElement temporalExtent = new JSONLDElement(new JSONObject());
		temporalExtent.setId("chnode:temporalExtentID");
		temporalExtent.add("http://www.charme.org.uk/def/hasCalendar",
				createPredicate(null, null, "Gregorian"));
		temporalExtent.add(
				"http://www.charme.org.uk/def/hasStart",
				createPredicate(null,
						"http://www.w3.org/2001/XMLSchema#dateTime", annotation
								.getSpecificResource().getSelector()
								.getValidityStart()));
		temporalExtent.add(
				"http://www.charme.org.uk/def/hasStop",
				createPredicate(null,
						"http://www.w3.org/2001/XMLSchema#dateTime", annotation
								.getSpecificResource().getSelector()
								.getValidityStop()));

		return temporalExtent;
	}

	/**
	 * Creates the "chnode:subsetSelectorID" element of the annotation. For
	 * example:
	 * 
	 * { 
	 * 	 "@id": "chnode:subsetSelectorID", 
	 *   "@type": [
	 *         "http://www.charme.org.uk/def/SubsetSelector" 
	 *    ],
	 *    "http://strdf.di.uoa.gr/ontology#hasGeometry": [ 
	 *    		{ 
	 *    			"@type": "http://strdf.di.uoa.gr/ontology#WKT", 
	 *    			"@value": "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>" 
	 *    		} 
	 *    ],
	 *    "http://www.charme.org.uk/def/hasTemporalExtent": [ 
	 *    	{ 
	 *    		"@id": "chnode:temporalExtentID" 
	 *    	} 
	 *    ],
	 *    "http://www.charme.org.uk/def/hasVariables": [ 
	 *    	{ 
	 *    		"@value": "sst" 
	 *    	}, 
	 *    	{
	 * 			"@value": "chlor_a" 
	 * 		} 
	 *   ] 
	 * },
	 * 
	 * 
	 * @return The JSON-LD Element that represents a chnode:subsetSelectorID
	 *         node.
	 */
	private JSONLDSubsetSelector createSubSelector(Annotation annotation) {
		JSONLDSubsetSelector ss = new JSONLDSubsetSelector(new JSONObject());
		ss.setHasGeometry(annotation.getSpecificResource().getSelector()
				.getGeometry());
		ss.setHasVariables(annotation.getSpecificResource().getSelector()
				.getVariables());
		return ss;
	}

	public JSONLDSubsetSelector getSubsetSelector() {
		return new JSONLDSubsetSelector(findElementWithId("chnode:subsetSelectorID"));
	}

	/**
	 * Creates the target JSONValue representing the annoID element and its
	 * auxiliar data (creating time, motivation, etc) in JSON-LD format,
	 * following the W3C OA standard and the CHARMe datamodel.
	 * 
	 *   {
     *     "@id": "chnode:annoID",
     *     "@type": [
     *        "http://www.w3.org/ns/oa#Annotation"
     *      ],
     *      "http://www.w3.org/ns/oa#annotatedAt": [
     *        {
     *          "@value": "2014-05-12T11:30:00Z"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#annotatedBy": [
     *        {
     *          "@id": "http://pk904866"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#hasBody": [
     *        {
     *          "@id": "chnode:bodyID"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#hasTarget": [
     *        {
     *          "@id": "chnode:datasetSubsetID"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#motivatedBy": [
     *        {
     *          "@id": "http://www.w3.org/ns/oa#linking"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#serializedAt": [
     *        { 
     *          "@value": "2013-12-28T12:00:00Z"
     *        }
     *      ],
     *      "http://www.w3.org/ns/oa#serializedBy": [
     *        {
     *          "@id": "chnode:agentID"
     *        }
     *      ]
     *   },
	 * 
	 * @return a JSONValue representing the annoID element of the annotation.
	 */
	private JSONLDElement createAnno(Annotation annotation) {
		JSONLDElement anno = new JSONLDElement(new JSONObject());
		anno.setId("chnode:annoID");
		anno.setTypeAsArray("http://www.w3.org/ns/oa#Annotation");
		anno.add("http://www.w3.org/ns/oa#annotatedAt",
				createPredicate(null, null, "TODO")); // TODO
		anno.add("http://www.w3.org/ns/oa#annotatedBy",
				createPredicate("TODO", null, null)); // TODO
		anno.add("http://www.w3.org/ns/oa#hasBody",
				createPredicate("chnode:bodyID", null, null));
		anno.add("http://www.w3.org/ns/oa#hasTarget",
				createPredicate("chnode:datasetSubsetID", null, null));
		anno.add(
				"http://www.w3.org/ns/oa#motivatedBy",
				createPredicate(
						"http://www.w3.org/ns/oa#" + annotation.getMotivation(),
						null, null));
		anno.add("http://www.w3.org/ns/oa#serializedAt",
				createPredicate(null, null, "TODO")); // TODO
		anno.add("http://www.w3.org/ns/oa#serializedBy",
				createPredicate("chnode:agentID", null, null));

		return anno;
	}

	/**
	 * Creates the target JSONValue representing the specific resource element
	 * of the annotation in JSON-LD format.
	 * 
	 *   {
     *     "@id": "chnode:datasetSubsetID",
     *     "@type": [
     *       "http://www.w3.org/ns/oa#SpecificResource"
     *     ],
     *     "http://www.w3.org/ns/oa#hasSelector": [
     *       {
     *         "@id": "chnode:subsetSelectorID"
     *       }
     *     ],
     *     "http://www.w3.org/ns/oa#hasSource": [
     *       {
     *          "@id": "http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png"
     *       }
     *     ]
     *   },
	 * 
	 * @return a JSONValue representing the specific resource of the annotation
	 *         is a subset of.
	 */
	private JSONLDElement createDataSubset(Annotation annotation) {
		JSONLDElement datasetSubset = new JSONLDElement(new JSONObject());
		datasetSubset.setId("chnode:datasetSubsetID");
		datasetSubset
				.setTypeAsArray("http://www.w3.org/ns/oa#SpecificResource");
		datasetSubset.add("http://www.w3.org/ns/oa#hasSelector",
				createPredicate("chnode:subsetSelectorID", null, null)); // TODO: Charme Node doesn't understand this yet
		datasetSubset
				.add("http://www.w3.org/ns/oa#hasSource",
						createPredicate(
								"http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png",
								null, null)); // TODO: Should come from Godiva

		return datasetSubset;
	}

	/**
	 * Creates the agent element of the annotation in JSON-LD format.
	 * 
	 *  {
     *    "@id": "chnode:agentID",
     *    "@type": [
     *       "http://www.w3.org/ns/prov#SoftwareAgent"
     *    ],
     *    "http://xmlns.com/foaf/0.1/name": [
     *      {
     *        "@value": "CHARMe FGC Tool v0.1"
     *      }
     *    ]
     *  },
	 * 
	 * 
	 * @return a JSONValue representing the agent software that has created the
	 *         annotation, the CHARMe Maps Tool, in this case.
	 */
	private JSONLDElement createAgent(Annotation annotation) {
		JSONLDElement agent = new JSONLDElement(new JSONObject());
		agent.setId("chnode:agentID");
		agent.setTypeAsArray("http://www.w3.org/ns/prov#SoftwareAgent");
		agent.add("http://xmlns.com/foaf/0.1/name",
				createPredicate(null, null, "CHARMe Maps Tool v0.1b"));

		return agent;
	}

	/**
	 * Creates the target dataset element of the annotation in JSON-LD format.
	 * 
	 *   {
     *     "@id": "http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png",
     *     "@type": [
     *       "http://www.charme.org.uk/def/dataset"
     *     ]
     *   },
	 * 
	 * @return a JSONValue representing the dataset the target of the annotation
	 *         is a subset of.
	 */
	private JSONLDElement createTargetDataset(Annotation annotation) {

		JSONLDElement targetDataset = new JSONLDElement(new JSONObject());
		targetDataset
				.setId("http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png");
		targetDataset.setTypeAsArray("http://www.charme.org.uk/def/dataset");

		return targetDataset;
	}

	/**
	 * Creates the author element of the annotation in JSON-LD format.
	 * 
     * {
     *    "@id": "http://pk904866",
     *    "@type": [
     *      "http://xmlns.com/foaf/0.1/Person"
     *    ],
     *    "http://xmlns.com/foaf/0.1/mbox": [
     *      {
     *        "@id": "mailto:r.alegre@reading.ac.uk"
     *      }
     *    ],
     *    "http://xmlns.com/foaf/0.1/name": [
     *      {
     *        "@value": "Raquel Alegre"
     *      }
     *    ]
     * }
	 * 
	 * @return a JSONValue representing the author of the annotation.
	 */
	private JSONLDElement createAuthor(Annotation annotation) {
		JSONLDElement author = new JSONLDElement(new JSONObject());
		author.setId("http://pk904866");
		author.setTypeAsArray("http://xmlns.com/foaf/0.1/Person");
		author.add("http://xmlns.com/foaf/0.1/mbox",
				createPredicate("mailto:r.alegre@reading.ac.uk", null, null)); // TODO: Charme Node doesn't understand this yet
		author.add("http://xmlns.com/foaf/0.1/name",
				createPredicate(null, null, "Raquel Alegre")); // TODO: Should come from OAuth? Node? HTTPUrl?

		return author;
	}

}
