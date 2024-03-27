package com.zsgs.interviewpanel.datalayer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Panel;


public class PanelDatabase {
	private static PanelDatabase panelDatabase;
	List<Candidate> candidateList = getList("list.json");
	List<Candidate> selectedList = getList("selected.json");
	ArrayDeque<Candidate> candidatesList =getCandidateList();
	LinkedHashMap<Integer, String> feedbackList = readFeedbackFile();
	boolean isFree = readIsFree();
	private Panel panel;

	public static PanelDatabase getInstance() {
		if (panelDatabase == null) {
			panelDatabase = new PanelDatabase();
		}
		return panelDatabase;
	}

	public boolean isInterviewerFree() {
		return readIsFree();
	}

	public List<Candidate> getAllCandidateList() {
		return getList("list.json");
	}

	public Panel getPanel() {
		panel=readJsonFile();
		return panel;
	
	}

	private void save() {
		JSONArray jsonArray = new JSONArray();

		JSONObject libJson = new JSONObject();
		libJson.put("Id", panel.getId());
		libJson.put("email", panel.getEmailId());
		libJson.put("teamName", panel.getTeamName());
		jsonArray.add(libJson);
		try (FileWriter file = new FileWriter("teamDetails.json")) {
			file.write(jsonArray.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void insertPanel(Panel panel) {
		this.panel = panel;
		save();
	}

	public ArrayDeque<Candidate> getAllCandidates() {
		return getCandidateList();
	}

	public boolean selectedCandidate(int candidateId) {
		for (Candidate candidate : candidatesList) {
			if (candidate.getId() == candidateId) {
				selectedList.add(candidate);
				writeJsonCandidate("selected.json",selectedList);
				return true;
			}
		}
		return false;

	}

	public List<Candidate> selectedList() {
		return getList("selected.json");

	}

	public boolean scheduleInterview() {
		if (readIsFree() == false) {
			return false;
		} else {
			isFree = false;
			writeIsFree();
			return true;
		}
	}

	public Candidate viewCurrentCandidate() {
		return getCandidateList().peek();
	}

	public boolean removeCandidate() {
		if (candidatesList.size() != 0) {
			isFree = true;
			writeIsFree();
			candidatesList.remove();
			writeJsonCandidate("candidates.json");
			return true;
		}
		return false;
	}

	public void getFeedBack(int candidateId, String Feedback) {
		feedbackList.put(candidateId, Feedback);
		writeFeedback();
	}

	public boolean insertCandidate(Candidate candidate) {
		boolean hasCandidate = false;
		for (Candidate addedCandidate : getCandidateList()) {
			if (addedCandidate.getName().equals(candidate.getName())
					&& addedCandidate.getEmailId().equals(candidate.getEmailId())) {

				hasCandidate = true;
				break;
			}
		}
		if (hasCandidate) {

			return false;
		} else {
			candidatesList.add(candidate);
			candidateList.add(candidate);
			writeJsonCandidate("list.json",candidateList);
			writeJsonCandidate("candidates.json");
			return true;
		}
	}

	public LinkedHashMap<Integer, String> getFeedBack() {
		return readFeedbackFile();
	}
	private Panel readJsonFile() {
		Panel l = new Panel();
		File file = new File("teamDetails.json");
        if (!file.exists() || file.length() == 0) {
            return l;
        }

		try {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("teamDetails.json"));
			for (Object obj : jsonArray) {
				JSONObject jsonObject = (JSONObject) obj;
				long id = (long) jsonObject.get("Id");
				String emailId = (String) jsonObject.get("email");
				String name = (String) jsonObject.get("teamName");
				l.setId((int) id);
				l.setEmailId(emailId);
				l.setTeamName(name);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return l;
	}
	 private  void writeJsonCandidate(String filename,List<Candidate> list) {
	        JSONArray jsonArray = new JSONArray();

	        for (Candidate candidate : list) {
	            JSONObject candidateJson = new JSONObject();
	            candidateJson.put("Id",candidate.getId());
	            candidateJson.put("name", candidate.getName());
	            candidateJson.put("email", candidate.getEmailId());
	            candidateJson.put("degree",candidate.getDegree());
	            candidateJson.put("location",candidate.getLocation());
	            jsonArray.add(candidateJson);
	        }

	        try (FileWriter file = new FileWriter(filename)) {
	            file.write(jsonArray.toJSONString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 private  void writeJsonCandidate(String filename) {
	        JSONArray jsonArray = new JSONArray();
	        for (Candidate candidate : candidatesList) {
	            JSONObject candidateJson = new JSONObject();
	            candidateJson.put("Id",candidate.getId());
	            candidateJson.put("name", candidate.getName());
	            candidateJson.put("email", candidate.getEmailId());
	            candidateJson.put("degree",candidate.getDegree());
	            candidateJson.put("location",candidate.getLocation());
	            jsonArray.add(candidateJson);
	        }

	        try (FileWriter file = new FileWriter(filename)) {
	            file.write(jsonArray.toJSONString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 private ArrayDeque<Candidate> getCandidateList() {
			ArrayDeque<Candidate> candidate = new ArrayDeque<Candidate>();
			File file = new File("candidates.json");
	        if (!file.exists() || file.length() == 0) {
	            return candidate;
	        }
			try {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("candidates.json"));
				for (Object obj : jsonArray) {
					JSONObject jsonObject = (JSONObject) obj;
					long id = (long) jsonObject.get("Id");
					String name = (String) jsonObject.get("name");
					String email = (String) jsonObject.get("email");
					String degree=(String) jsonObject.get("degree");
					String location=(String) jsonObject.get("location");
					Candidate b=new Candidate();
					b.setId((int)id);
					b.setName(name);
					b.setEmailId(email);
					b.setDegree(degree);
					b.setLocation(location);
					candidate.add(b);
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}

			return candidate;
		}
	 
	 private List<Candidate> getList(String filename) {
			List<Candidate> candidate = new ArrayList<Candidate>();
			File file = new File(filename);
	        if (!file.exists() || file.length() == 0) {
	            return candidate;
	        }
			try {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));
				for (Object obj : jsonArray) {
					JSONObject jsonObject = (JSONObject) obj;
					long id = (long) jsonObject.get("Id");
					String name = (String) jsonObject.get("name");
					String email = (String) jsonObject.get("email");
					String degree=(String) jsonObject.get("degree");
					String location=(String) jsonObject.get("location");
					Candidate b=new Candidate();
					b.setId((int)id);
					b.setName(name);
					b.setEmailId(email);
					b.setDegree(degree);
					b.setLocation(location);
					candidate.add(b);
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}

			return candidate;
		}
	 private void writeFeedback()
	 {
		 JSONObject jsonFeedback = new JSONObject();
	        for (Integer i:feedbackList.keySet()) {
	            jsonFeedback.put(i.toString(), feedbackList.get(i));
	        }
	        try (FileWriter file = new FileWriter("feedback.json")) {
	            file.write(jsonFeedback.toJSONString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 private LinkedHashMap<Integer, String> readFeedbackFile() {
		 LinkedHashMap<Integer, String> linked=new LinkedHashMap<Integer, String>();
		 File file = new File("feedback.json");
	        if (!file.exists() || file.length() == 0) {
	            return linked;
	        }
	        try {
	        	FileReader fileReader = new FileReader("feedback.json");
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

	            for (Object key : jsonObject.keySet()) {
	                Integer k = Integer.parseInt((String) key);
	                String value = (String) jsonObject.get(key);
	                linked.put(k, value);
	            }
	        } catch (IOException | ParseException e) {
	            e.printStackTrace();
	        }
		return linked;
	 }
	 public void writeIsFree()
	 {
		 JSONObject booleanJson = new JSONObject();
	        booleanJson.put("isFree", isFree);

	        try (FileWriter file = new FileWriter("isFree.json")) {
	            file.write(booleanJson.toJSONString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 public boolean readIsFree()
	 {
		 boolean free=true;
		 File file = new File("isFree.json");
	        if (!file.exists() || file.length() == 0) {
	            return true;
	        }
	        try  {
	        	FileReader fileReader = new FileReader("isFree.json");
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

	            if (jsonObject.containsKey("isFree")) {
	                isFree = (boolean) jsonObject.get("isFree");
	            } 
	        } catch (IOException | ParseException e) {
	            e.printStackTrace();
	        }

	        return isFree;
	 }
	 
}
