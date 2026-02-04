package lambda_expression;
@FunctionalInterface
interface LightAction{
	void Activate();
	
}
public class SmartHomeLighting {
	public static void main(String[] args) {
		LightAction motionTrigger=() ->
		System.out.println("Motion detected lights on at full brightness");
		LightAction nightTimeTrigger=() ->
		System.out.println("Night Time lights on at dim");
		LightAction voiceCommandTrigger =() ->
		System.out.println("Voice command changing light color to Blue");
		
		activateLight(motionTrigger);
		activateLight(nightTimeTrigger);
		activateLight(voiceCommandTrigger);
	}
	static void activateLight(LightAction action) {
		action.Activate();
	}

}
